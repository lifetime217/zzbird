* 根据传入的Boos角色Id 查询学生
queryComUserByBoosRole
===
select 
	@pageTag(){
		t_user.id user_roleid,
		t_user.role_name user_name,
		t_user.role_headimg user_img,
		t_user.role_val user_roleval,
		t_user.company_id user_company_id,
		tcom.company_name user_company_name
	@}
from t_xcx_user_role comUser
left join t_xcx_user_role t_user ON comUser.company_id = t_user.company_id
inner join t_company tcom on t_user.company_id = tcom.id
	@where(){
		comUser.isdelete = 0
	and t_user.isdelete = 0
	and comUser.role_val = 10
	and t_user.role_val = #roleType#
	and comUser.id = #roleId#
	@} 
	
	

queryCourseByUserRoleId
===
* 根据传入的学生角色Id 学生在本公司下的课程
select
	tcc.id course_id,
	tcc.course_name course_name
from t_company_course_user tccu
INNER JOIN t_company_course tcc on tccu.company_course_id = tcc.id
where tccu.xcx_user_role_id = #roleId#
ORDER BY tcc.id
