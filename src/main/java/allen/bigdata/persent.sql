select 
    t1.userid
    ,case when  t2.recall_is_realauth is null then '3' else t2.recall_is_realauth  end as recall_is_realauth
    ,case when  t3.recall_reg_time is null then '8' else t3.recall_reg_time  end as recall_reg_time  --注册时间（首次登录贷超）
    ,case when  t2.recall_realauth_time is null then '8' else t2.recall_realauth_time  end as recall_realauth_time  
from
(
    select 
    58id as userid
    from hdp_jinrong_qiangui_defaultdb.ctf_xfjr_history_vs_app_action_90d_user_001 where daystr='${todaySuffix}'
    group by 58id
) t1
left join 
(
select 
user_id,
case when realname_auth_time is not null then '1' else '2' end as recall_is_realauth,
case  when recall_realauth_time=0 then '1'
when recall_realauth_time=1 then '2'
when (recall_realauth_time<=3 and recall_realauth_time>1) then '3' 
when (recall_realauth_time<=7 and recall_realauth_time>3) then '4' 
when (recall_realauth_time<=14 and recall_realauth_time>7) then '5' 
when (recall_realauth_time<=30 and recall_realauth_time>14) then '6' 
when recall_realauth_time>30  then '7' else '8' end as recall_realauth_time
from
	(
	select
	58id as user_id,
	realname_time as realname_auth_time,
	datediff(${today},substr(realname_time,1,10)) as recall_realauth_time
	from hdp_jinrong_qiangui_defaultdb.stf_usr_dc2_xfjr_realname 
	where daystr="$bash{date -d'${todaySuffix} -1 day ' +%Y%m%d}" 

	) t
) t2 on t1.userid=t2.user_id
left join 
(
select 
user_id,
case when recall_reg_time=0 then '1' 
when recall_reg_time=1 then '2' 
when (recall_reg_time<=3 and recall_reg_time>1) then '3' 
when (recall_reg_time<=7 and recall_reg_time>3) then '4' 
when (recall_reg_time<=14 and recall_reg_time>7) then '5' 
when (recall_reg_time<=30 and recall_reg_time>14) then '6' 
when recall_reg_time>30 then '7' else '8' end as recall_reg_time
from(
	select 
	58id as user_id,
	datediff(${today},substr(create_time,1,10)) as recall_reg_time
	from 
	(
	select 
	t20.58id,
	t20.create_time,
	ROW_NUMBER() OVER(PARTITION by t20.58id order by t20.create_time) as rnk
	from 
		(
		select 
		user_id as 58id,
		substr(create_time,1,19) as create_time 
		from hdp_jinrong_qiangui_defaultdb.kfpt_t_portal_user where daystr='20190709' 
		union all 
		select 58uid as 58id,
		substr(create_time,1,19) as create_time 
		from hdp_jinrong_qiangui_defaultdb.ctd_yh_xfjr_customer_info_001 where daystr='20190709'
		union all 
		select user_id as 58id,
		from_unixtime(cast(dt_ts_event/1000 as int),'yyyy-MM-dd HH:mm:ss') as create_time 
		from hdp_jinrong_qiangui_defaultdb.itf_dc_wmda_log where daystr >='20190710'
		) t20
	) t21 
	where t21.rnk=1
	)t33
) t3 on t1.userid=t3.user_id;


--back c1标签备份

select 
    t1.userid
    ,case when  t2.recall_is_realauth is null then '3' else t2.recall_is_realauth  end as recall_is_realauth
    ,case when  t2.recall_reg_time is null then '8' else t2.recall_reg_time  end as recall_reg_time  --注册时间（首次登录贷超）
    ,case when  t2.recall_realauth_time is null then '8' else t2.recall_realauth_time  end as recall_realauth_time  
from
(

    select 
    userid
    from hdp_jinrong_qiangui_defaultdb.stf_app_action_90d_001 where daystr='${todaySuffix}'
    group by userid

) t1
left join 
(
select 
user_id,
case when realname_auth_time is not null then '1' else '2' end as recall_is_realauth,
case when recall_reg_time=0 then '1' 
when recall_reg_time=1 then '2' 
when (recall_reg_time<=3 and recall_reg_time>1) then '3' 
when (recall_reg_time<=7 and recall_reg_time>3) then '4' 
when (recall_reg_time<=14 and recall_reg_time>7) then '5' 
when (recall_reg_time<=30 and recall_reg_time>14) then '6' 
when recall_reg_time>30 then '7' else '8' end as recall_reg_time,
case  when recall_realauth_time=0 then '1'
when recall_realauth_time=1 then '2'
when (recall_realauth_time<=3 and recall_reg_time>1) then '3' 
when (recall_realauth_time<=7 and recall_reg_time>3) then '4' 
when (recall_realauth_time<=14 and recall_reg_time>7) then '5' 
when (recall_realauth_time<=30 and recall_reg_time>14) then '6' 
when recall_realauth_time>30  then '7' else '8' end as recall_realauth_time
from
(
select
user_id,
realname_auth_time,
datediff(${today},substr(create_time,1,10)) as recall_reg_time,
datediff(${today},substr(realname_auth_time,1,10)) as recall_realauth_time
from hdp_jinrong_qiangui_defaultdb.kfpt_t_portal_user 
where daystr="$bash{date -d'${todaySuffix} -1 day ' +%Y%m%d}" 

) t
) t2 on t1.userid=t2.user_id;