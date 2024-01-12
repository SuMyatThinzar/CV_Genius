package com.smtz.cvgenius.data.data_source;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CVGeniusDatabase_Impl extends CVGeniusDatabase {
  private volatile CVDao _cVDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(17) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Cv` (`templateId` INTEGER NOT NULL, `profileImage` BLOB, `personalDetails` TEXT, `educationDetails` TEXT NOT NULL, `workExperiences` TEXT NOT NULL, `skills` TEXT NOT NULL, `achievements` TEXT NOT NULL, `objective` TEXT, `projectDetails` TEXT NOT NULL, `references` TEXT NOT NULL, `signature` TEXT, `cvId` INTEGER NOT NULL, PRIMARY KEY(`cvId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b84fdc4b8f1bee73a94be6d719b26f1e')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Cv`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCv = new HashMap<String, TableInfo.Column>(12);
        _columnsCv.put("templateId", new TableInfo.Column("templateId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("profileImage", new TableInfo.Column("profileImage", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("personalDetails", new TableInfo.Column("personalDetails", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("educationDetails", new TableInfo.Column("educationDetails", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("workExperiences", new TableInfo.Column("workExperiences", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("skills", new TableInfo.Column("skills", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("achievements", new TableInfo.Column("achievements", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("objective", new TableInfo.Column("objective", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("projectDetails", new TableInfo.Column("projectDetails", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("references", new TableInfo.Column("references", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("signature", new TableInfo.Column("signature", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCv.put("cvId", new TableInfo.Column("cvId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCv = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCv = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCv = new TableInfo("Cv", _columnsCv, _foreignKeysCv, _indicesCv);
        final TableInfo _existingCv = TableInfo.read(_db, "Cv");
        if (! _infoCv.equals(_existingCv)) {
          return new RoomOpenHelper.ValidationResult(false, "Cv(com.smtz.cvgenius.domain.model.CvVO).\n"
                  + " Expected:\n" + _infoCv + "\n"
                  + " Found:\n" + _existingCv);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "b84fdc4b8f1bee73a94be6d719b26f1e", "209a5452ed9ee143700e83d1fb5f9a57");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Cv");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Cv`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CVDao.class, CVDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public CVDao cvDao() {
    if (_cVDao != null) {
      return _cVDao;
    } else {
      synchronized(this) {
        if(_cVDao == null) {
          _cVDao = new CVDao_Impl(this);
        }
        return _cVDao;
      }
    }
  }
}
