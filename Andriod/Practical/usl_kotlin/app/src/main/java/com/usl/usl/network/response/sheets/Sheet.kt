package com.usl.usl.network.response.sheets

import java.util.*
import kotlin.collections.HashMap

class Sheet {
    var id:Int?=null
    var game_id:Int?=null
    var cell_amounts:HashMap<Any,Any>?=null
    var m_total:Double?=null
    var d_total:Double?=null
    var h_total:Double?=null
    var grand_total:Double?=null
    var sheet_type:String?=null
    var player_id:Int?=null
    var creator_id:Int?=null
    var submitted_to_id:Objects?=null
    var merged_sheets:List<Objects>?=null
    var level:Objects?=null
    var created_at:String?=null
    var updated_at:String?=null
    var status:String?=null
    var submitted_level_two_sheet_to_admin:Boolean?=null
    var cutting_type:Objects?=null
    var commission_1:Objects?=null
    var commission_2:Objects?=null
    var commission_3:Objects?=null
    var custom_cutting:Boolean?=null
    var editable:Boolean?=null
    var deletable:Boolean?=null
    var resulted:Boolean?=null
}