package com.usl.usl.network.response.user

class User {

    var id:Int=0
    var roleid:Int=0
    var password_changed_on_first_login:Boolean=false
    var active:Boolean=false
    var email:String?=null
    var name:String?=null
    var user_id:Int=0
    var created_by_user_id:Int=0
    var phoneNumber:String?=null
    var balance:Float=0.toFloat()
    var exposure:String?=null
    var auth_token:String?=null

}