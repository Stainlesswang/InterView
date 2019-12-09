ADD JAR hdfs://hdp-58-cluster/home/hdp_58dp/udf/fbu-udf-170911xu.jar;
CREATE TEMPORARY FUNCTION fbu_decode_xffq AS 'com.bj58.fbuudf.date.DeCoderTransXFFQUdf';
insert overwrite table hdp_jinrong_qiangui_defaultdb.ctf_xfjr_haojie_user_001 partition(daystr='${dateSuffix}')
select 
	t1.wb_id as wb_id
	,case when t1.phone is null or t1.phone='' or t1.phone='null' then ''  	else t1.phone end as phone
	,case when t2.customer_id is not null then '1' 	else '0' end as hasCredit
	,case 
		when t2.expire_date is null or t2.expire_date='' or t2.expire_date='null' then ''
		else substring(t2.expire_date,1,19)
	end as creditExpireDate
	,case 
		when t2.apply_complete_time is null or t2.apply_complete_time='' or t2.apply_complete_time='null' then ''
		else substring(t2.apply_complete_time,1,19)
	end as credit_date
	,coalesce(t2.audit_quota,0) as credit_limit
	,case 
		when t3.customer_id is not null and substring(t3.apply_time,1,19)>=substring(t2.apply_complete_time,1,19) then '1' 
		when t3.customer_id is not null then '2'
		else '0' 
	end as hasWithDrawApplyCurrCredit
	,case 
		when t5.customer_id is not null and substring(t5.lend_time,1,19)>=substring(t2.apply_complete_time,1,19) then '1' 
		else '0'  
	end as hasWithDrawCurrCredit
	,case 
		when t4.vist_time is null or t4.vist_time='' or t4.vist_time='null' then ''
		else substring(t4.vist_time,1,19)
	end as vist_time
	,case when t6.userid is not null and t6.reject_day <= 0 then 1
	      else 0
	end as is_in_reject_valid_date
	,case when t6.reject_day = 1 then 1
	      when t6.reject_day = 3 then 3
		  when t6.reject_day = 6 then 6
		  when t6.reject_day = 14 then 14
		  when t6.reject_day = 30 then 30
		  when t6.reject_day = 60 then 60
		  when t6.reject_day = 90 then 90
		  when t6.reject_day = 120 then 120
		  when t6.reject_day = 150 then 150
		  else 0
	end as reject_day 
	,CASE when  t7.bmdl_score < 0.09 then 0
          when  t7.bmdl_score >= 0.09  then 1
		  else 10
     end as score_id
	,case when t8.hist_max_late_days < 5 then 1
	      when t8.hist_max_late_days > 5 then 2
	      else 0
	end as max_late_days
	,case when t9.limittype in ('提额','额度开户') then 1
	      when t9.limittype not in ('提额','额度开户') and t9.userid is not null then 2
	      else 0
	end as is_normal_limit
	,case when t9.limit_day = 1 then 1
	      when t9.limit_day = 3 then 3
		  when t9.limit_day = 6 then 6
		  when t9.limit_day = 14 then 14
		  when t9.limit_day = 30 then 30
		  when t9.limit_day = 60 then 60
		  when t9.limit_day = 90 then 90
		  when t9.limit_day = 120 then 120
		  when t9.limit_day = 150 then 150
		  else 0
	end as limit_day	
	,case when t10.payoff_days is null then '10'
	      else t10.payoff_days
	end as payoff_days
	,case when t11.58id is not null then '1'
	     else '0'
	end as is_in_loan
from 
(
 select  58id AS userid
 FROM  hdp_jinrong_qiangui_defaultdb.ctf_xfjr_history_vs_app_action_90d_user_001 
 where daystr='${dateSuffix}'
) t0 --九十天活跃+金融全量用户
left join(
(
	select 58uid as wb_id,customer_id,fbu_decode_xffq(mobile) as phone
	from hdp_jinrong_qiangui_defaultdb.ctd_yh_xfjr_customer_info_001
	where daystr='${dateSuffix}'
) t1 --用户表
on t0.userid=t1.wb_id
left join(
	select 
		a.customer_id
		,a.apply_complete_time
		,a.audit_quota
		,a.quota_valid_period_array as expire_date
		,row_number() over (partition by a.customer_id order by a.apply_complete_time desc) rn
	from hdp_jinrong_qiangui_defaultdb.ctf_fk_xfjr_quota_application_001 a
	where a.daystr='${dateSuffix}' and audit_result='0'
) t2 --授信通过表
on t1.customer_id=t2.customer_id and t2.rn=1
left join (
	select 
		customer_id
		,create_time as apply_time
		,row_number() over (partition by a.customer_id order by a.create_time desc) rn
	from hdp_jinrong_qiangui_defaultdb.ctf_dd_xfjr_order_001 a
	where a.daystr='${dateSuffix}' and a.loan_status<>'0'
) t3 --最近提现申请表
on t1.customer_id=t3.customer_id and t3.rn=1
left join (
	select 58id,max(daystr) as vist_time
	from (
		select user_id as 58id,max(dt) as daystr
		from wbdb.dm_wmda_log_ev_3289916654594 a
		where a.dt<='${dateSuffix}'
		group by user_id
		union all
		select user_id as 58id,max(daystr) as daystr
		from  hdp_jinrong_qiangui_defaultdb.stf_yh_xfjr_user_behavior_001  a
		where a.daystr<='${dateSuffix}'
		group by user_id
	) a
	group by 58id	
) t4 --访问时间
on t1.wb_id=t4.58id
left join (
	select 
		customer_id
		,lend_time as lend_time
		,row_number() over (partition by a.customer_id order by a.lend_time desc) rn
	from hdp_jinrong_qiangui_defaultdb.ctf_dd_xfjr_order_001 a
	where a.daystr='${dateSuffix}' and a.loan_status='4' and a.loan_progress='900'
) t5 --最近提现成功表
on t1.customer_id=t5.customer_id and t5.rn=1
left join (
    SELECT 
        userid,
		datediff(${today},rejectvaliddate) as reject_day
	 from hdp_jinrong_cl.wdRejectDeadline
)t6 --提现拒绝有效期
on t1.customer_id=t6.userid
left join (
    SELECT 
        userid,
		bmdl_score
	 from hdp_jinrong_cl.bscr_v1
)t7 --B模型分
 on t1.customer_id=t7.userid  
left join (
    SELECT 
        58uid,
		hist_max_late_days
	from hdp_jinrong_cl.loan001_maxdlqdays_user
)t8 --最大逾期天数
on t1.customer_id=t8.58uid  
left join (
    SELECT 
        userid,
		limittype,
		datediff(${today}, limitvaliddt) as limit_day
	from hdp_jinrong_cl.limitCenter
	group by userid,limittype,limitvaliddt
)t9 --额度状态
on t1.customer_id=t9.userid  
left join 
(
select 
get_json_object(reserve_fields,'$.58id') as 58id, 
max(get_json_object(reserve_fields,'$.payoff_date')) as payoff_date,
datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date'))),
case when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=1 then '1'
     when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=7 then '2'
     when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=14 then '3'
     when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=30 then '4'
     when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=60 then '5'
     when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=90 then '6'
     when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=120 then '7'
     when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=150 then '8'
     when datediff(${today},max(get_json_object(reserve_fields,'$.payoff_date')))=180 then '9'
else '10' end as payoff_days 
from
hdp_jinrong_qiangui_defaultdb.ctf_ngw_fin_iou 
where daystr = '${dateSuffix}' and bizstr = 'haojie' and get_json_object(reserve_fields,'$.loan_status_id') ='S' 
and get_json_object(reserve_fields,'$.58id') <> '' and get_json_object(reserve_fields,'$.58id') is not null 
group by get_json_object(reserve_fields,'$.58id')
) t10
on t1.customer_id=t10.58id
left join 
(
select
get_json_object(reserve_fields,'$.58id') as 58id
from
hdp_jinrong_qiangui_defaultdb.ctf_ngw_fin_iou 
where daystr = '${dateSuffix}' and bizstr = 'haojie' and get_json_object(reserve_fields,'$.loan_status_id') !='S'
and get_json_object(reserve_fields,'$.58id') <> '' and get_json_object(reserve_fields,'$.58id') is not null 
group by get_json_object(reserve_fields,'$.58id')
)t11
on t1.customer_id=t11.58id