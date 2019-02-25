findNames
===
* 根据年龄查找匹配的用户姓名列表

	select name from test where 1=1 
	@if(!isEmpty(age)){
	and age < #age#
	@} 
	
findNick
===
* 根据昵称查找用户列表

	select * from test where 1=1 
	@if(!isEmpty(nick)){
	and nick like #'%'+nick+'%'#
	@} 
	