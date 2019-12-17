update `fsmart_schedule_info` set hour='17',minute='02',second='10' where `day_start`<='2019-12-07' and `day_end`>='2019-12-07' and `is_valid` = 1;


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


alter table hdp_jinrong_qiangui_defaultdb.stf_user_fsmart_c24_001   add if not exists partition (daystr='${todaySuffix}')
LOCATION 'viewfs://58-cluster/home/hdp_jinrong_qiangui/resultdata/fsmart/fsmart_data_import/c24/${todaySuffix}';