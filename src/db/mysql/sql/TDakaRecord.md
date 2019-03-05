queryDatePage
===
select 
@pageTag(){
	DATE_FORMAT(tdr.daka_time,'%Y-%m') monthDate,
	UNIX_TIMESTAMP( DATE_FORMAT(tdr.daka_time,'%Y-%m-01 00:00:00')) statDate,
	UNIX_TIMESTAMP( DATE_FORMAT( LAST_DAY(tdr.daka_time),'%Y-%m-%d 23:59:59')) endDate
@}
from t_daka_record tdr
inner join t_company_course tcc on tdr.company_course_id = tcc.id
@where(){
	tdr.isdelete = 0
	and tdr.student_id = #openId#
	and tcc.company_id = #companyId#
	and tdr.daka_time > (select from_unixtime(#statDate#))
	and tdr.daka_time < (select from_unixtime(#endDate#))
	GROUP BY monthDate
@}
