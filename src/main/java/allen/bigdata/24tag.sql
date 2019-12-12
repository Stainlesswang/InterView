set mapreduce.job.reduces=500;
set hive.auto.convert.join = true;
set hive.exec.parallel=true;

dfs -mkdir -p /home/hdp_jinrong_qiangui/resultdata/fsmart/fsmart_data_import/c24/${todaySuffix};
dfs -rm -r /home/hdp_jinrong_qiangui/resultdata/fsmart/fsmart_data_import/c24/${todaySuffix};
insert overwrite directory '/home/hdp_jinrong_qiangui/resultdata/fsmart/fsmart_data_import/c24/${todaySuffix}'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\001'
select 
t1.58id as userid,
case
	 WHEN t3.mdlvar1 is null then '0'
	 WHEN t3.mdlvar1<0  then '1'
     WHEN 0<=t3.mdlvar1<=450  then '2'
     WHEN 450<t3.mdlvar1<=500  then '3'
     WHEN 500<t3.mdlvar1<=550  then '4'
     WHEN 550<t3.mdlvar1<=600  then '5'
     WHEN 600<t3.mdlvar1<=650  then '6'
     WHEN 650<t3.mdlvar1  then '7'
else '0' end as pre_a_score
from  
(
 select 58id
 FROM  hdp_jinrong_qiangui_defaultdb.ctf_xfjr_history_vs_app_action_90d_user_001
 where daystr='${dateSuffix}'
)t1
left join 
(
	SELECT t30.mdlvar1,t30.userid
	FROM
	(
	SELECT mdlvar1,userid
	from
	(select max(daystr) as latest_day from hdp_jinrong_cl.xjmdl_whitelistmdlvars where daystr is not null)a
		JOIN hdp_jinrong_cl.xjmdl_whitelistmdlvars b
		on a.latest_day=b.daystr where b.daystr is not null
	)t30
)t3
on t1.58id=t3.userid










ADD JAR hdfs://hdp-58-cluster/home/hdp_58dp/udf/fbuudf-1.0.jar;
CREATE TEMPORARY FUNCTION fbu_json_parse AS 'com.bj58.fbuudf.parse.JsonParseUdf';


SELECT t111.debt_in_time,count(t111.debt_in_time)
FROM
(
select 
t1.58id as userid,
case
     WHEN t2.credit_refuse_expired_time is not null and unix_timestamp(t2.credit_refuse_expired_time,'yyyy-MM-dd HH:mm:ss')>=unix_timestamp() then '1'
     when t2.credit_refuse_expired_time is not null and unix_timestamp(t2.credit_refuse_expired_time,'yyyy-MM-dd HH:mm:ss')<unix_timestamp() then '0'
else '2' end as credit_in_time,
case WHEN t3.debt_refuse_expired_date is not null and  unix_timestamp(t3.debt_refuse_expired_date,'yyyy-MM-dd HH:mm:ss')>=unix_timestamp() then '1'
     when t3.debt_refuse_expired_date is not null and  unix_timestamp(t3.debt_refuse_expired_date,'yyyy-MM-dd HH:mm:ss')<unix_timestamp() then '0'
else '2' end as debt_in_time
from  
(
 select DISTINCT 58id
 FROM  hdp_jinrong_qiangui_defaultdb.ctf_xfjr_history_vs_app_action_90d_user_001
 where daystr='${dateSuffix}'
)t1
left join 
(
	SELECT t20.58credit_id,
	max(t20.credit_refuse_expired_time) AS credit_refuse_expired_time
	FROM
	(
	SELECT fbu_json_parse(t6.reserve_fields,'58id') as 58credit_id,
	 fbu_json_parse(t6.reserve_fields,'credit_refuse_expired_time') as credit_refuse_expired_time
	from hdp_jinrong_qiangui_defaultdb.ctf_ngw_ord_apply t6 
	where t6.daystr='${dateSuffix}' and t6.bizstr='haojie'
	)t20
	WHERE credit_refuse_expired_time<>''
	GROUP BY t20.58credit_id
)t2
on t1.58id=t2.58credit_id
left join 
(
	SELECT t30.58debt_id,
	max(t30.debt_refuse_expired_date) AS debt_refuse_expired_date
	FROM
	(
	SELECT fbu_json_parse(t7.reserve_fields,'58id') as 58debt_id,
	 fbu_json_parse(t7.reserve_fields,'debt_refuse_expired_date') as debt_refuse_expired_date
	from hdp_jinrong_qiangui_defaultdb.ctf_ngw_ord_order t7
	where t7.daystr='${dateSuffix}' and t7.bizstr='haojie'
	)t30
	WHERE debt_refuse_expired_date<>''
	GROUP BY t30.58debt_id
)t3
on t1.58id=t3.58debt_id

)t111
group by t111.debt_in_time