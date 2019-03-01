queryPage
===
*  公司列表普通用户分页查询sql

	select
	@pageTag(){
	id,
	company_name,
	company_adress,
	company_simple_address,
	telphone,
	lat,
	lng,
	geohash,
	industry_list_id,
	industry_list_name,
	banner_imgs,
	sign
	@}
	from t_company
	where 1 = 1 
	@if(!isEmpty(showV1)){
	 and show_v1 = #showV1#
	@}
	@if(!isEmpty(search)){
	 and CONCAT(IFNUll(`company_name`,''),IFNUll(`company_adress`,''),IFNUll(`industry_list_name`,'')) LIKE CONCAT('%',#search#,'%')
	@}
	
	
queryPointUser
===
* 查询重点用户
	
	SELECT
	t_company.id,
	t_company.company_name,
	t_company.company_adress,
	t_company.company_simple_address,
	t_company.telphone,
	t_company.lat,
	t_company.lng,
	t_company.geohash,
	t_company.industry_list_id,
	t_company.industry_list_name,
	t_company.banner_imgs,
	t_company.sign
	FROM
	t_company
	WHERE
	t_company.show_v1 = 1



	

	