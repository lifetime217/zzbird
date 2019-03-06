queryPunchMonth
===
select 
	DATE_FORMAT(tdr.daka_time, '%Y-%m')  monthDate,
	COUNT(tdr.id) punCount,
	UNIX_TIMESTAMP( DATE_FORMAT(tdr.daka_time,'%Y-%m-01 00:00:00')) statDate,
	UNIX_TIMESTAMP( DATE_FORMAT( LAST_DAY(tdr.daka_time),'%Y-%m-%d 23:59:59')) endDate
from t_daka_record tdr
inner join t_company_course tcc on tdr.company_course_id = tcc.id
where
	tdr.isdelete = 0
	and tdr.student_id = #roleId#
	and tdr.daka_time > (select from_unixtime(#statDate#))
	and tdr.daka_time < (select from_unixtime(#endDate#))
	GROUP BY monthDate

	
queryPunchList
===

select
	tdr.id,
	tdr.company_course_id,
	tdr.teacher_id,
	tdr.student_id,
	tdr.daka_time,
	tdr.isdelete,
	txur.role_name teaName,
	tcc.course_name courseName
from t_daka_record tdr
inner join t_xcx_user_role txur on tdr.teacher_id = txur.id
inner join t_company_course tcc on tdr.company_course_id = tcc.id
where 
tdr.isdelete = 0
and txur.isdelete = 0
and tdr.student_id = #roleId#
and tdr.daka_time > (select from_unixtime(#statDate#))
and tdr.daka_time < (select from_unixtime(#endDate#))


queryPunchCourseList
===
SELECT
	tdr.company_course_id courseid,
	tcc.course_name coursename,
	count(tdr.company_course_id) puncount
FROM t_daka_record tdr
INNER JOIN t_xcx_user_role txur on tdr.teacher_id = txur.id
INNER JOIN t_company_course tcc on tdr.company_course_id = tcc.id
WHERE tdr.isdelete = 0
and txur.isdelete = 0
and tdr.student_id = #roleId#
GROUP BY tdr.company_course_id



queryPunchListByCourse
===
SELECT
	tdr.daka_time,
	txur.role_name teaname,
	tdr.company_course_id,
	tcc.course_name coursename
FROM t_daka_record tdr
INNER JOIN t_xcx_user_role txur on tdr.teacher_id = txur.id
INNER JOIN t_company_course tcc on tdr.company_course_id = tcc.id
WHERE tdr.isdelete = 0
and txur.isdelete = 0
and tdr.student_id = #roleId#
and tcc.id = #courseId#