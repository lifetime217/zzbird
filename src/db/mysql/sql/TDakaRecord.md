queryPunchMonth
===
* 查询打卡的月并且分组排序
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
	order by monthDate desc

	
queryPunchList
===
* 按月查询打卡记录
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
order by tdr.daka_time desc


queryPunchCourseList
===
* 查询课程的列
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
order by tdr.daka_time desc


queryTeaInfo
===
* 查询老师详情
select
	txur.id,
	txur.role_headimg,
	txur.role_name
from t_company_course_user tccu
inner join t_xcx_user_role txur on tccu.xcx_user_role_id = txur.id
where
txur.isdelete = 0
and tccu.xcx_user_role_id = #roleId#
and tccu.company_course_id = #courseId#
and txur.role_val = 20


queryTeaDakaTime
===
* 查询老师打的卡天数
SELECT
	count(timess.tiems) days
FROM
	(
		SELECT
			DATE_FORMAT(tdr.daka_time, '%Y-%m-%d') tiems
		FROM
			t_daka_record tdr
		WHERE
			tdr.isdelete = 0
		AND tdr.teacher_id = #roleId#
		AND tdr.company_course_id = #courseId#
		GROUP BY tiems
	) timess
	
queryYidaka
===
* 查询已打卡

SELECT
	tdr.id daka_id,
	txur.id,
	txur.role_headimg,
	txur.role_name
FROM
	t_daka_record tdr
INNER JOIN t_xcx_user_role txur ON tdr.student_id = txur.id
WHERE
	txur.isdelete = 0
AND tdr.isdelete = 0
AND txur.role_val = 30
AND tdr.company_course_id = #courseId#
AND TO_DAYS(tdr.daka_time) = TO_DAYS(NOW())


queryWeidaka
===
* 查询未打卡

SELECT
	txur.id,
	txur.role_name,
	txur.role_headimg
from t_company_course_user tccu 
inner join t_xcx_user_role txur on tccu.xcx_user_role_id = txur.id
where 
txur.isdelete = 0
and tccu.company_course_id = #courseId#
and txur.role_val = 30
@if(list.~size != 0){
	and txur.id not IN (
		@for(id in list){
			#id#  #text(idLP.last?"":"," )#
		@}
	#text(")")#
@}

queryUserClassHour
===
* 查询老师、企业、学生的打卡总课时(如传入课程id查询学生的单个课程的总课时)
	SELECT
	SUM(CAST(LEFT(c.course_hour,1) AS SIGNED))
	FROM
	t_daka_record AS r ,
	t_company_course AS c
	WHERE
	r.isdelete = 0 
	AND r.company_course_id = c.id
	@if(roleVal==30){
		 and r.student_id = #roleId#
	@} 
	@if(roleVal==20){
		 and r.teacher_id = #roleId#
	@} 
	@if(!isEmpty(courseId)){
		 and r.company_course_id = #courseId#
	@}
	@if(!isEmpty(companyId)){
		 and  c.company_id = #companyId#
	@} 
	 ORDER BY r.daka_time
	 
	 
queryDakaWeekCount
===
	* 查询公司下的课程打卡总共打卡了多少周
	
	SELECT
		WEEKOFYEAR(MAX(tdr.daka_time)) - WEEKOFYEAR(MIN(tdr.daka_time)) + 1
	FROM
		t_daka_record tdr
	INNER JOIN t_company_course tcc ON tdr.company_course_id = tcc.id
	WHERE
		tdr.isdelete = 0
		and tcc.company_id = #companyId#
		
		
queryClassHourthIsMonth
===
	* 查询学生的本月课时
	SELECT
		SUM(CAST(LEFT(c.course_hour,1) AS SIGNED))
		FROM
		t_daka_record AS r ,
		t_company_course AS c
		WHERE
	r.isdelete =0
	AND r.company_course_id = c.id
	AND r.student_id = #roleId#
	AND r.daka_time > (DATE_FORMAT( CURDATE(), '%Y-%m-01 00:00:00'))
	AND r.daka_time < (DATE_FORMAT( LAST_DAY(CURDATE()), '%Y-%m-%d 23:59:59'))
	ORDER BY r.daka_time

	
