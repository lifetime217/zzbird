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
		and geohash like CONCAT(left(geohash,6),'%') or geohash like CONCAT(#geohashList[0]#,'%') or geohash like CONCAT(#geohashList[1]#,'%') or geohash like CONCAT(#geohashList[2]#,'%') or geohash like CONCAT(#geohashList[3]#,'%') or geohash like CONCAT(#geohashList[4]#,'%') 
		or geohash like CONCAT(#geohashList[5]#,'%') or geohash like CONCAT(#geohashList[6]#,'%') or geohash like CONCAT(#geohashList[7]#,'%') 
		@}
	@}
	

	
	
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
	t_company.sign,
	t_company.lat,
	t_company.lng,
	t_company.xcx_user_id
	FROM
	t_company
	WHERE
	t_company.id = #companyId#

	
updateCompanyPersonNumber
===
* 修改公司人数加一  0代表修改老师人数  1代表修改学生人数 

update t_company
set  
 @if(flag==0){
     teacher_count=teacher_count+1 
 @}
 @if(flag==1){
     student_count=student_count+1
 @}
 where id =#id#
    
	

	

	