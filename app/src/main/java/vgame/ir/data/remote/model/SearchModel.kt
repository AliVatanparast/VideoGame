package vgame.ir.data.remote.model

import vgame.ir.data.model.Teacher

class SearchModel(var type: TYPE?) {

    var teacher: Teacher? = null
    var course: Course? = null

    enum class TYPE {
        Course, Teacher
    }
}
