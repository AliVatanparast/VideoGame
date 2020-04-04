package vgame.ir.view.ui.main.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vgame.ir.data.remote.model.Course
import vgame.ir.data.remote.model.CoursesResponse
import vgame.ir.data.remote.model.LessonDetailResponse
import vgame.ir.databinding.ItemSearchCourseListBinding
import vgame.ir.databinding.ItemSearchLessonListBinding
import vgame.ir.databinding.ItemSearchTeacherListBinding
import vgame.ir.view.base.BaseAdapter
import java.text.Normalizer


class SearchListAdapter(var callback: Callback) : BaseAdapter<RecyclerView.ViewHolder, Any>() {

    companion object {
        private const val TEAM_COURSE = 0
        private const val TEAM_TEACHER = 1
        private const val TEAM_LESSON = 2
    }

    interface Callback {
        fun onClicked(item: Any)
    }

    private var items: List<Any> = emptyList()

    override fun setData(items: List<Any>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TEAM_COURSE -> {
                CourseViewHolder.create(callback,LayoutInflater.from(viewGroup.context), viewGroup)
            }
            TEAM_TEACHER -> {
                TeacherHolder.create(callback,LayoutInflater.from(viewGroup.context), viewGroup)
            }
            TEAM_LESSON -> {
                LessonViewHolder.create(callback,LayoutInflater.from(viewGroup.context), viewGroup)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is CourseViewHolder -> viewHolder.onBind(items[position] as Course)
            is TeacherHolder -> viewHolder.onBind(items[position] as CoursesResponse.Teacher)
            is LessonViewHolder -> {
                viewHolder.onBind(items[position] as LessonDetailResponse.Session)
               // setAnimation(viewHolder.itemView, position)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = items[position]
        return when (comparable) {
            is Course -> TEAM_COURSE
            is CoursesResponse.Teacher -> TEAM_TEACHER
            is LessonDetailResponse.Session -> TEAM_LESSON
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    class CourseViewHolder(var callback: Callback,var binding: ItemSearchCourseListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Course) {
            binding.txtTitle.text = colorString(item.title+"", item.hight_light.toString())
            binding.txtDescription.text = colorString(item.description+"", item.hight_light.toString())
            try {
                binding.image = item.attachments!!.get(0).absoluteUrl!!.replace("attachments/courses/" , "")
            }catch (e:Exception){}

            binding.root.setOnClickListener {
                callback.onClicked(item)
            }

            binding.executePendingBindings()
        }
        @SuppressLint("DefaultLocale")
        fun colorString(originalText: String, search: String): CharSequence? {
            val normalizedText: String = Normalizer.normalize(originalText, Normalizer.Form.NFD).replace("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase()
            var start: Int = normalizedText.indexOf(search, 0, true)
            if (start < 0) {
                // not found, nothing to to
                return originalText
            } else {
                var highlighted = SpannableString(originalText)

                while (start >= 0) {
                    val spanStart = Math.min(start, originalText.length)
                    val spanEnd: Int = Math.min(start + search.length, originalText.length)

                    highlighted.setSpan(BackgroundColorSpan(Color.GREEN), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    start = normalizedText.indexOf(search, spanEnd)
                }

                return highlighted
            }
        }
        companion object {

            fun create(callback: Callback,inflater: LayoutInflater, parent: ViewGroup): CourseViewHolder {
                val binding = ItemSearchCourseListBinding.inflate(inflater, parent, false)
                return CourseViewHolder(callback,binding)
            }
        }
    }

    class TeacherHolder(var callback: Callback,var binding: ItemSearchTeacherListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CoursesResponse.Teacher) {
            binding.txtName.text = colorString(item.fullName!!, item.hight_light!!)
            binding.txtDescription.text = colorString(item.description!!, item.hight_light!!)
            binding.avatar = item.avatarUrl!!.replace("attachments/teachers/" , "")
            binding.root.setOnClickListener {
                callback.onClicked(item)
            }
            binding.executePendingBindings()
        }

        @SuppressLint("DefaultLocale")
        fun colorString(originalText: String, search: String): CharSequence? {
            val normalizedText: String = Normalizer.normalize(originalText, Normalizer.Form.NFD).replace("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase()
            var start: Int = normalizedText.indexOf(search, 0, true)
            if (start < 0) {
                // not found, nothing to to
                return originalText
            } else {
                var highlighted = SpannableString(originalText)

                while (start >= 0) {
                    val spanStart = Math.min(start, originalText.length)
                    val spanEnd: Int = Math.min(start + search.length, originalText.length)

                    highlighted.setSpan(BackgroundColorSpan(Color.GREEN), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    start = normalizedText.indexOf(search, spanEnd)
                }

                return highlighted
            }
        }

        companion object {

            fun create(callback: Callback,inflater: LayoutInflater, parent: ViewGroup): TeacherHolder {
                val binding = ItemSearchTeacherListBinding.inflate(inflater, parent, false)
                return TeacherHolder(callback,binding)
            }
        }
    }

    class LessonViewHolder(var callback: Callback,var binding: ItemSearchLessonListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: LessonDetailResponse.Session) {
            binding.txtTitle.text = colorString(item.title + "", item.hight_light!!)
            binding.txtDescription.text = colorString(item.description + "", item.hight_light!!)

            binding.root.setOnClickListener {
                callback.onClicked(item)
            }

            binding.image = item.course_image
            // binding.txtTitle.text = apply(item.description , "لورم")
            binding.executePendingBindings()
        }

        @SuppressLint("DefaultLocale")
        fun colorString(originalText: String, search: String): CharSequence? {
            val normalizedText: String = Normalizer.normalize(originalText, Normalizer.Form.NFD).replace("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase()
            var start: Int = normalizedText.indexOf(search, 0, true)
            if (start < 0) {
                // not found, nothing to to
                return originalText
            } else {
                var highlighted = SpannableString(originalText)

                while (start >= 0) {
                    val spanStart = Math.min(start, originalText.length)
                    val spanEnd: Int = Math.min(start + search.length, originalText.length)

                    highlighted.setSpan(BackgroundColorSpan(Color.GREEN), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    start = normalizedText.indexOf(search, spanEnd)
                }

                return highlighted
            }
        }

        companion object {

            fun create(callback: Callback,inflater: LayoutInflater, parent: ViewGroup): LessonViewHolder {
                val binding = ItemSearchLessonListBinding.inflate(inflater, parent, false)
                return LessonViewHolder(callback,binding)
            }
        }
    }

    fun setAnimation(itemView: View, p: Int) {
        /* var i: Int = p
         if (!on_attach) {
             i = 1
         }
         i++
         YoYo.with(Techniques.RubberBand)
                 .duration((500 * i).toLong())
                 .playOn(itemView)*/

        var i: Int = p

        if (!on_attach) {
            i = -1
        }
        val isNotFirstItem = i == -1
        i++
        itemView.alpha = 0f
        val animatorSet = AnimatorSet()
        val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 0.5f, 1.0f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        animator.startDelay = if (isNotFirstItem) 400 / 2 else (i * 400 / 3).toLong()
        animator.duration = 400
        animatorSet.play(animator)
        animator.start()
    }
}
