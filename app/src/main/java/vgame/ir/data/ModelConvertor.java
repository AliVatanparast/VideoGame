package vgame.ir.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LifecycleRegistryOwner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vgame.ir.data.local.dao.RavasiDao;
import vgame.ir.data.local.entity.BannerEntity;
import vgame.ir.data.local.entity.CategoryEntity;
import vgame.ir.data.local.entity.CollegeEntity;
import vgame.ir.data.local.entity.CourseEntity;
import vgame.ir.data.local.entity.DefaultNoteEntity;
import vgame.ir.data.local.entity.LessonEntity;
import vgame.ir.data.local.entity.UnivercityEntity;
import vgame.ir.data.model.CourseWithNotesModel;
import vgame.ir.data.remote.model.BannerResponse;
import vgame.ir.data.remote.model.CategoryResponse;
import vgame.ir.data.remote.model.CollegeResponse;
import vgame.ir.data.remote.model.Course;
import vgame.ir.data.remote.model.HomeResponse;
import vgame.ir.data.remote.model.LessonDetailResponse;
import vgame.ir.data.remote.model.UnivercityResponse;

public class ModelConvertor implements LifecycleRegistryOwner {

    private final RavasiDao ravasiDao;
    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    public ModelConvertor(RavasiDao ravasiDao) {
        this.ravasiDao = ravasiDao;
    }

    public static List<BannerEntity> getBannerEntity(BannerResponse bannerResponses) {
        List<BannerEntity> bannerEntities = new ArrayList<>();

        for (int i = 0; i < bannerResponses.getData().getSliders().size(); i++) {
            BannerEntity bannerEntity = new BannerEntity();
            bannerEntity.setUrl(bannerResponses.getData().getSliders().get(i).getAttachments().get(0).getAbsoluteUrl());
            bannerEntity.setId(bannerResponses.getData().getSliders().get(i).getId());
            bannerEntities.add(bannerEntity);
        }

        return bannerEntities;
    }

    public static List<UnivercityEntity> getUnivercityEntity(HomeResponse homeResponse) {
        List<UnivercityEntity> univercityEntities = new ArrayList<>();

        for (int i = 0; i < homeResponse.getData().getUnivercities().size(); i++) {
            UnivercityResponse univercityResponse = homeResponse.getData().getUnivercities().get(i);
            UnivercityEntity univercityEntity = new UnivercityEntity();

            univercityEntity.setName(univercityResponse.getName());
            univercityEntity.setId(univercityResponse.getId());

            if (univercityResponse.getAttachments().size() > 0) {
                univercityEntity.setUrl(univercityResponse.getAttachments().get(0).getAbsoluteUrl());
            }

            univercityEntities.add(univercityEntity);
        }

        return univercityEntities;
    }

    public static List<CollegeEntity> getColleges(HomeResponse homeResponse) {
        List<CollegeEntity> collegeEntities = new ArrayList<>();

        for (int i = 0; i < homeResponse.getData().getUnivercities().size(); i++) {
            List<CollegeResponse> responses = homeResponse.getData().getUnivercities().get(i).getCollegeResponses();

            for (int j = 0; j < responses.size(); j++) {
                CollegeEntity collegeEntity = new CollegeEntity();
                collegeEntity.setName(responses.get(j).getName());
                collegeEntity.setId(responses.get(j).getId());
                collegeEntity.setUni_id(responses.get(j).getCollegeId());

                collegeEntities.add(collegeEntity);
            }
        }

        return collegeEntities;
    }

    public static List<CategoryEntity> getCategories(HomeResponse homeResponse) {
        List<CategoryEntity> arrayList = new ArrayList<>();

        for (int i = 0; i < homeResponse.getData().getUnivercities().size(); i++) {
            for (int j = 0; j < homeResponse.getData().getUnivercities().get(i).getCollegeResponses().size(); j++) {
                List<CategoryResponse> responses = homeResponse.getData().getUnivercities().get(i).getCollegeResponses().get(j).getCategories();

                for (int k = 0; k < responses.size(); k++) {
                    CategoryEntity entity = new CategoryEntity();
                    entity.setName(responses.get(k).getName());
                    entity.setId(responses.get(k).getId());
                    entity.setCollege_id(responses.get(k).getMajorId());

                    arrayList.add(entity);
                }
            }
        }

        return arrayList;
    }

    public static CourseEntity getCourse(Course course) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(course.getId());
        courseEntity.setName(course.getTitle());
        courseEntity.setUrl((course.getAttachments().size() > 0) ? course.getAttachments().get(0).getAbsoluteUrl() : "");

        return courseEntity;
    }

    public static List<CourseEntity> getCourses(HomeResponse homeResponse) {
        List<CourseEntity> arrayList = new ArrayList<>();

        for (int i = 0; i < homeResponse.getData().getUnivercities().size(); i++) {
            for (int j = 0; j < homeResponse.getData().getUnivercities().get(i).getCollegeResponses().size(); j++) {
                for (int l = 0; l < homeResponse.getData().getUnivercities().get(i).getCollegeResponses().get(j).getCategories().size(); l++) {
                    List<Course> responses = homeResponse.getData().getUnivercities().get(i).getCollegeResponses().get(j).getCategories().get(l).getCourses();

                    for (int k = 0; k < responses.size(); k++) {
                        CourseEntity entity = new CourseEntity();
                        entity.setName(responses.get(k).getTitle());
                        entity.setId(responses.get(k).getId());
                        entity.setCategory_id(responses.get(k).getCategoryId());
                        entity.setMembers_count(responses.get(k).getMembers_count());
                        entity.setTeacher_name(responses.get(k).getTeacher().getFullName());

                        if (responses.get(k).getAttachments().size() > 0) {
                            String url = responses.get(k).getAttachments().get(0).getAbsoluteUrl();
                            entity.setUrl(url);
                        }

                        arrayList.add(entity);
                    }
                }
            }
        }

        return arrayList;
    }

    public static LessonEntity getLesson(String lesson_name, LessonDetailResponse lessonDetailResponse) {
        LessonEntity lessonEntity = new LessonEntity();
        LessonDetailResponse.Session session = lessonDetailResponse.getData().getSession();

        lessonEntity.setDescription(session.getDescription());
        lessonEntity.setCourse_chapter_id(session.getCourseChapterId());
        lessonEntity.setId(session.getId());
        lessonEntity.setTitle(lesson_name);
        lessonEntity.setVoice_comment((session.getVoiceComment() != null) ? session.getVoiceComment().getBody() : "");
        lessonEntity.setVoise_url(session.getAttachments().get(0).getAbsoluteUrl());
        //lessonEntity.setWord_url(session.getAttachments().get(1).getAbsoluteUrl());

        return lessonEntity;
    }

    public List<CourseWithNotesModel> getCoursesWithNotes(List<DefaultNoteEntity> noteEntities) {
        List<CourseWithNotesModel> res = new ArrayList<>();

        while (noteEntities.size() > 0) {
            int lesson_id = noteEntities.get(0).getCourse_id();

            List<DefaultNoteEntity> temp = new ArrayList<>();
            for (int j = 0; j < noteEntities.size(); j++) {
                if (lesson_id == noteEntities.get(j).getCourse_id()) {
                    temp.add(noteEntities.get(j));
                    noteEntities.remove(j);
                    j--;
                }
            }

            CourseWithNotesModel course = new CourseWithNotesModel();
            course.setNoteEntityList(temp);

            res.add(course);
        }

        return res;
    }

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
