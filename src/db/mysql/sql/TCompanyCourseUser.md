queryComStudentByBoosRole
===
select 
	@pageTag(){
		stuUser.id stu_roleid,
		stuUser.role_name stu_name,
		stuUser.role_headimg stu_img,
		stuUser.role_val stu_roleval,
		stuUser.company_id stu_company_id,
		tcom.company_name stu_company_name
	@}
from t_xcx_user_role comUser
left join t_xcx_user_role stuUser ON comUser.company_id = stuUser.company_id
inner join t_company tcom on stuUser.company_id = tcom.id
	@where(){
		comUser.isdelete = 0
	and stuUser.isdelete = 0
	and comUser.role_val = 10
	and stuUser.role_val = 30
	and comUser.id = #roleId#
	@} 

queryCourseByStuRoleId
===
select
	tcc.id course_id,
	tcc.course_name course_name
from t_company_course_user tccu
INNER JOIN t_company_course tcc on tccu.company_course_id = tcc.id
where tccu.xcx_user_role_id = #roleId#
ORDER BY tcc.id








queryComTeacherByBoosRole
===
select 
	@pageTag(){
		teaUser.id tea_roleid,
		teaUser.role_name tea_name,
		teaUser.role_headimg tea_img,
		teaUser.role_val tea_roleval,
		teaUser.company_id tea_company_id,
		tcom.company_name tea_company_name
	@}
from t_xcx_user_role comUser
left join t_xcx_user_role teaUser ON comUser.company_id = teaUser.company_id
inner join t_company tcom on teaUser.company_id = tcom.id
	@where(){
		comUser.isdelete = 0
	and teaUser.isdelete = 0
	and comUser.role_val = 10
	and teaUser.role_val = 20
	and comUser.id = #roleId#
	@} 

queryCourseByTeaRoleId
===
select
	tcc.id course_id,
	tcc.course_name course_name
from t_company_course_user tccu
INNER JOIN t_company_course tcc on tccu.company_course_id = tcc.id
where tccu.xcx_user_role_id = #roleId#
ORDER BY tcc.id




queryCourseUserByCourseId
===
* 根据传入的课程id和角色身份来查询这个课程下的学生或者老师
	SELECT
		tx.role_name name,
		tx.role_headimg headImg
		FROM
		t_company_course_user AS tc ,
		t_xcx_user_role AS tx
		WHERE
		tc.xcx_user_role_id = tx.id AND
		tc.company_course_id = #courseId# AND
		tx.role_val = #roleVal#
		ORDER BY
		tc.add_time DESC	

