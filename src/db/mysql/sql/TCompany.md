queryPage
===
*  公司列表普通用户分页查询sql

	select
		@pageTag(){
		id,
		company_name,
		company_address, 
		company_simple_address,
		telphone,
		lat,
		lng,
		geohash,
		industry_list_id,
		industry_list_name,
		banner_imgs,
		teacher_count,
		student_count,
		sign
	@}
	from t_company
	@where(){
		@if(!isEmpty(showV1)){
		 and show_v1 = #showV1#
		@}
		@if(!isEmpty(search)){
		 and CONCAT(IFNUll(`company_name`,''),IFNUll(`company_address`,''),IFNUll(`industry_list_name`,'')) LIKE CONCAT('%',#search#,'%')
		@}
		@if(!isEmpty(geohashList)){
		and geohash like CONCAT(#geohashList[0]#,'%') or geohash like CONCAT(#geohashList[1]#,'%') or geohash like CONCAT(#geohashList[2]#,'%') or geohash like CONCAT(#geohashList[3]#,'%') or geohash like CONCAT(#geohashList[4]#,'%') 
		or geohash like CONCAT(#geohashList[5]#,'%') or geohash like CONCAT(#geohashList[6]#,'%') or geohash like CONCAT(#geohashList[7]#,'%') 
		@}
	@}
	
queryTeacher
===
*  测试查询一对多关系查询  @orm.many({"id":"companyId"},"TCompany.queryTeacher","TXcxUserRole");
	SELECT
	t_xcx_user_role.id,
	t_xcx_user_role.role_name,
	t_xcx_user_role.role_headimg,
	t_xcx_user_role.xcx_user_id,
	t_xcx_user_role.sign
	FROM
	t_xcx_user_role
	WHERE
	t_xcx_user_role.company_id=#companyId#
	and  role_val = 20
	
	
	
queryPointUser
===
* 查询重点用户
	
	SELECT
	t_company.id,
	t_company.company_name,
	t_company.company_address,
	t_company.company_simple_address,
	t_company.telphone,
	t_company.lat,
	t_company.lng,
	t_company.geohash,
	t_company.industry_list_id,
	t_company.industry_list_name,
	t_company.banner_imgs,
	t_company.teacher_count,
	t_company.student_count,
	t_company.sign
	FROM
	t_company
	WHERE
	t_company.show_v1 = 1
	
	
queryCompanyDetail
===
* 查询公司详情
	
	SELECT
	t_company.id,
	t_company.company_name,
	t_company.company_address,
	t_company.company_simple_address,
	t_company.telphone,
	t_company.company_detail_info,
	t_company.look_count,
	t_company.share_count,
	t_company.love_count,
	t_company.industry_list_id,
	t_company.industry_list_name,
	t_company.banner_imgs,
	t_company.teacher_count,
	t_company.student_count,
	t_company.sign
	FROM
	t_company
	WHERE
	t_company.id = #companyId#

	


	

	