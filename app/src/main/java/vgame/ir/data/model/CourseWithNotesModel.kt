package vgame.ir.data.model

import vgame.ir.data.local.entity.CourseEntity
import vgame.ir.data.local.entity.DefaultNoteEntity

class CourseWithNotesModel {
    var courseEntity: CourseEntity? = null
    var noteEntityList: List<DefaultNoteEntity>? = null
}
