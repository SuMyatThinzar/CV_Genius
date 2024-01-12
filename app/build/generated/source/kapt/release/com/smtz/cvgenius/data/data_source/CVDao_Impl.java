package com.smtz.cvgenius.data.data_source;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.smtz.cvgenius.data.typeConverters.AchievementTypeConverter;
import com.smtz.cvgenius.data.typeConverters.EducationDetailTypeConverter;
import com.smtz.cvgenius.data.typeConverters.PersonalDetailTypeConverter;
import com.smtz.cvgenius.data.typeConverters.ProjectDetailTypeConverter;
import com.smtz.cvgenius.data.typeConverters.ReferenceTypeConverter;
import com.smtz.cvgenius.data.typeConverters.SkillTypeConverter;
import com.smtz.cvgenius.data.typeConverters.WorkExperienceTypeConverter;
import com.smtz.cvgenius.domain.model.AchievementVO;
import com.smtz.cvgenius.domain.model.CvVO;
import com.smtz.cvgenius.domain.model.EducationDetailVO;
import com.smtz.cvgenius.domain.model.PersonalDetailVO;
import com.smtz.cvgenius.domain.model.ProjectDetailVO;
import com.smtz.cvgenius.domain.model.ReferenceVO;
import com.smtz.cvgenius.domain.model.SkillsVO;
import com.smtz.cvgenius.domain.model.WorkExperienceVO;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CVDao_Impl implements CVDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CvVO> __insertionAdapterOfCvVO;

  private final PersonalDetailTypeConverter __personalDetailTypeConverter = new PersonalDetailTypeConverter();

  private final EducationDetailTypeConverter __educationDetailTypeConverter = new EducationDetailTypeConverter();

  private final WorkExperienceTypeConverter __workExperienceTypeConverter = new WorkExperienceTypeConverter();

  private final SkillTypeConverter __skillTypeConverter = new SkillTypeConverter();

  private final AchievementTypeConverter __achievementTypeConverter = new AchievementTypeConverter();

  private final ProjectDetailTypeConverter __projectDetailTypeConverter = new ProjectDetailTypeConverter();

  private final ReferenceTypeConverter __referenceTypeConverter = new ReferenceTypeConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteCV;

  public CVDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCvVO = new EntityInsertionAdapter<CvVO>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Cv` (`templateId`,`profileImage`,`personalDetails`,`educationDetails`,`workExperiences`,`skills`,`achievements`,`objective`,`projectDetails`,`references`,`signature`,`cvId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CvVO value) {
        stmt.bindLong(1, value.getTemplateId());
        if (value.getProfileImage() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getProfileImage());
        }
        final String _tmp = __personalDetailTypeConverter.toString(value.getPersonalDetails());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
        final String _tmp_1 = __educationDetailTypeConverter.toString(value.getEducationDetails());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
        final String _tmp_2 = __workExperienceTypeConverter.toString(value.getWorkExperiences());
        if (_tmp_2 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp_2);
        }
        final String _tmp_3 = __skillTypeConverter.toString(value.getSkills());
        if (_tmp_3 == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp_3);
        }
        final String _tmp_4 = __achievementTypeConverter.toString(value.getAchievements());
        if (_tmp_4 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_4);
        }
        if (value.getObjective() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getObjective());
        }
        final String _tmp_5 = __projectDetailTypeConverter.toString(value.getProjectDetails());
        if (_tmp_5 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, _tmp_5);
        }
        final String _tmp_6 = __referenceTypeConverter.toString(value.getReferences());
        if (_tmp_6 == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, _tmp_6);
        }
        if (value.getSignature() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getSignature());
        }
        stmt.bindLong(12, value.getCvId());
      }
    };
    this.__preparedStmtOfDeleteCV = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Cv WHERE cvId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertNewCV(final CvVO cvVO) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCvVO.insert(cvVO);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCV(final long cvId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCV.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, cvId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteCV.release(_stmt);
    }
  }

  @Override
  public LiveData<CvVO> getSingleCV(final long cvId) {
    final String _sql = "SELECT * FROM Cv WHERE cvId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cvId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Cv"}, false, new Callable<CvVO>() {
      @Override
      public CvVO call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTemplateId = CursorUtil.getColumnIndexOrThrow(_cursor, "templateId");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImage");
          final int _cursorIndexOfPersonalDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "personalDetails");
          final int _cursorIndexOfEducationDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "educationDetails");
          final int _cursorIndexOfWorkExperiences = CursorUtil.getColumnIndexOrThrow(_cursor, "workExperiences");
          final int _cursorIndexOfSkills = CursorUtil.getColumnIndexOrThrow(_cursor, "skills");
          final int _cursorIndexOfAchievements = CursorUtil.getColumnIndexOrThrow(_cursor, "achievements");
          final int _cursorIndexOfObjective = CursorUtil.getColumnIndexOrThrow(_cursor, "objective");
          final int _cursorIndexOfProjectDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "projectDetails");
          final int _cursorIndexOfReferences = CursorUtil.getColumnIndexOrThrow(_cursor, "references");
          final int _cursorIndexOfSignature = CursorUtil.getColumnIndexOrThrow(_cursor, "signature");
          final int _cursorIndexOfCvId = CursorUtil.getColumnIndexOrThrow(_cursor, "cvId");
          final CvVO _result;
          if(_cursor.moveToFirst()) {
            final int _tmpTemplateId;
            _tmpTemplateId = _cursor.getInt(_cursorIndexOfTemplateId);
            final byte[] _tmpProfileImage;
            if (_cursor.isNull(_cursorIndexOfProfileImage)) {
              _tmpProfileImage = null;
            } else {
              _tmpProfileImage = _cursor.getBlob(_cursorIndexOfProfileImage);
            }
            final PersonalDetailVO _tmpPersonalDetails;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfPersonalDetails)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfPersonalDetails);
            }
            _tmpPersonalDetails = __personalDetailTypeConverter.toPersonalDetailVO(_tmp);
            final List<EducationDetailVO> _tmpEducationDetails;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfEducationDetails)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfEducationDetails);
            }
            _tmpEducationDetails = __educationDetailTypeConverter.toEducationDetailVO(_tmp_1);
            final List<WorkExperienceVO> _tmpWorkExperiences;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfWorkExperiences)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfWorkExperiences);
            }
            _tmpWorkExperiences = __workExperienceTypeConverter.toWorkExperienceVO(_tmp_2);
            final List<SkillsVO> _tmpSkills;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfSkills)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfSkills);
            }
            _tmpSkills = __skillTypeConverter.toSkillsVO(_tmp_3);
            final List<AchievementVO> _tmpAchievements;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfAchievements)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfAchievements);
            }
            _tmpAchievements = __achievementTypeConverter.toAchievementVO(_tmp_4);
            final String _tmpObjective;
            if (_cursor.isNull(_cursorIndexOfObjective)) {
              _tmpObjective = null;
            } else {
              _tmpObjective = _cursor.getString(_cursorIndexOfObjective);
            }
            final List<ProjectDetailVO> _tmpProjectDetails;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfProjectDetails)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfProjectDetails);
            }
            _tmpProjectDetails = __projectDetailTypeConverter.toProjectDetailVO(_tmp_5);
            final List<ReferenceVO> _tmpReferences;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReferences)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReferences);
            }
            _tmpReferences = __referenceTypeConverter.toReferenceVO(_tmp_6);
            final String _tmpSignature;
            if (_cursor.isNull(_cursorIndexOfSignature)) {
              _tmpSignature = null;
            } else {
              _tmpSignature = _cursor.getString(_cursorIndexOfSignature);
            }
            final long _tmpCvId;
            _tmpCvId = _cursor.getLong(_cursorIndexOfCvId);
            _result = new CvVO(_tmpTemplateId,_tmpProfileImage,_tmpPersonalDetails,_tmpEducationDetails,_tmpWorkExperiences,_tmpSkills,_tmpAchievements,_tmpObjective,_tmpProjectDetails,_tmpReferences,_tmpSignature,_tmpCvId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<CvVO>> getAllCV() {
    final String _sql = "SELECT * FROM Cv";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Cv"}, false, new Callable<List<CvVO>>() {
      @Override
      public List<CvVO> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTemplateId = CursorUtil.getColumnIndexOrThrow(_cursor, "templateId");
          final int _cursorIndexOfProfileImage = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImage");
          final int _cursorIndexOfPersonalDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "personalDetails");
          final int _cursorIndexOfEducationDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "educationDetails");
          final int _cursorIndexOfWorkExperiences = CursorUtil.getColumnIndexOrThrow(_cursor, "workExperiences");
          final int _cursorIndexOfSkills = CursorUtil.getColumnIndexOrThrow(_cursor, "skills");
          final int _cursorIndexOfAchievements = CursorUtil.getColumnIndexOrThrow(_cursor, "achievements");
          final int _cursorIndexOfObjective = CursorUtil.getColumnIndexOrThrow(_cursor, "objective");
          final int _cursorIndexOfProjectDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "projectDetails");
          final int _cursorIndexOfReferences = CursorUtil.getColumnIndexOrThrow(_cursor, "references");
          final int _cursorIndexOfSignature = CursorUtil.getColumnIndexOrThrow(_cursor, "signature");
          final int _cursorIndexOfCvId = CursorUtil.getColumnIndexOrThrow(_cursor, "cvId");
          final List<CvVO> _result = new ArrayList<CvVO>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CvVO _item;
            final int _tmpTemplateId;
            _tmpTemplateId = _cursor.getInt(_cursorIndexOfTemplateId);
            final byte[] _tmpProfileImage;
            if (_cursor.isNull(_cursorIndexOfProfileImage)) {
              _tmpProfileImage = null;
            } else {
              _tmpProfileImage = _cursor.getBlob(_cursorIndexOfProfileImage);
            }
            final PersonalDetailVO _tmpPersonalDetails;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfPersonalDetails)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfPersonalDetails);
            }
            _tmpPersonalDetails = __personalDetailTypeConverter.toPersonalDetailVO(_tmp);
            final List<EducationDetailVO> _tmpEducationDetails;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfEducationDetails)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfEducationDetails);
            }
            _tmpEducationDetails = __educationDetailTypeConverter.toEducationDetailVO(_tmp_1);
            final List<WorkExperienceVO> _tmpWorkExperiences;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfWorkExperiences)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfWorkExperiences);
            }
            _tmpWorkExperiences = __workExperienceTypeConverter.toWorkExperienceVO(_tmp_2);
            final List<SkillsVO> _tmpSkills;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfSkills)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfSkills);
            }
            _tmpSkills = __skillTypeConverter.toSkillsVO(_tmp_3);
            final List<AchievementVO> _tmpAchievements;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfAchievements)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfAchievements);
            }
            _tmpAchievements = __achievementTypeConverter.toAchievementVO(_tmp_4);
            final String _tmpObjective;
            if (_cursor.isNull(_cursorIndexOfObjective)) {
              _tmpObjective = null;
            } else {
              _tmpObjective = _cursor.getString(_cursorIndexOfObjective);
            }
            final List<ProjectDetailVO> _tmpProjectDetails;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfProjectDetails)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfProjectDetails);
            }
            _tmpProjectDetails = __projectDetailTypeConverter.toProjectDetailVO(_tmp_5);
            final List<ReferenceVO> _tmpReferences;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReferences)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReferences);
            }
            _tmpReferences = __referenceTypeConverter.toReferenceVO(_tmp_6);
            final String _tmpSignature;
            if (_cursor.isNull(_cursorIndexOfSignature)) {
              _tmpSignature = null;
            } else {
              _tmpSignature = _cursor.getString(_cursorIndexOfSignature);
            }
            final long _tmpCvId;
            _tmpCvId = _cursor.getLong(_cursorIndexOfCvId);
            _item = new CvVO(_tmpTemplateId,_tmpProfileImage,_tmpPersonalDetails,_tmpEducationDetails,_tmpWorkExperiences,_tmpSkills,_tmpAchievements,_tmpObjective,_tmpProjectDetails,_tmpReferences,_tmpSignature,_tmpCvId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
