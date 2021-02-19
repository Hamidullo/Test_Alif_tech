package com.criminal_code.task.ui.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.criminal_code.task.ui.Model.Tasks;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Tasks> __insertionAdapterOfTasks;

  private final EntityDeletionOrUpdateAdapter<Tasks> __deletionAdapterOfTasks;

  private final EntityDeletionOrUpdateAdapter<Tasks> __updateAdapterOfTasks;

  public TaskDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTasks = new EntityInsertionAdapter<Tasks>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Tasks` (`uid`,`task`,`startTime`,`endTime`,`status`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Tasks value) {
        stmt.bindLong(1, value.uid);
        if (value.task == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.task);
        }
        stmt.bindLong(3, value.startTime);
        if (value.endTime == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.endTime);
        }
        final int _tmp;
        _tmp = value.status ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfTasks = new EntityDeletionOrUpdateAdapter<Tasks>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Tasks` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Tasks value) {
        stmt.bindLong(1, value.uid);
      }
    };
    this.__updateAdapterOfTasks = new EntityDeletionOrUpdateAdapter<Tasks>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Tasks` SET `uid` = ?,`task` = ?,`startTime` = ?,`endTime` = ?,`status` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Tasks value) {
        stmt.bindLong(1, value.uid);
        if (value.task == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.task);
        }
        stmt.bindLong(3, value.startTime);
        if (value.endTime == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.endTime);
        }
        final int _tmp;
        _tmp = value.status ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.uid);
      }
    };
  }

  @Override
  public void insert(final Tasks task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTasks.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Tasks task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTasks.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Tasks task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTasks.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Tasks> getAll() {
    final String _sql = "SELECT * FROM Tasks";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfTask = CursorUtil.getColumnIndexOrThrow(_cursor, "task");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final List<Tasks> _result = new ArrayList<Tasks>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Tasks _item;
        _item = new Tasks();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.task = _cursor.getString(_cursorIndexOfTask);
        _item.startTime = _cursor.getLong(_cursorIndexOfStartTime);
        _item.endTime = _cursor.getString(_cursorIndexOfEndTime);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfStatus);
        _item.status = _tmp != 0;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Tasks>> getAllLiveData() {
    final String _sql = "SELECT * FROM Tasks";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Tasks"}, false, new Callable<List<Tasks>>() {
      @Override
      public List<Tasks> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfTask = CursorUtil.getColumnIndexOrThrow(_cursor, "task");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<Tasks> _result = new ArrayList<Tasks>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Tasks _item;
            _item = new Tasks();
            _item.uid = _cursor.getInt(_cursorIndexOfUid);
            _item.task = _cursor.getString(_cursorIndexOfTask);
            _item.startTime = _cursor.getLong(_cursorIndexOfStartTime);
            _item.endTime = _cursor.getString(_cursorIndexOfEndTime);
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfStatus);
            _item.status = _tmp != 0;
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

  @Override
  public List<Tasks> loadAllByIds(final int[] taskIds) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT ");
    _stringBuilder.append("*");
    _stringBuilder.append(" FROM Tasks WHERE uid IN (");
    final int _inputSize = taskIds.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int _item : taskIds) {
      _statement.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfTask = CursorUtil.getColumnIndexOrThrow(_cursor, "task");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final List<Tasks> _result = new ArrayList<Tasks>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Tasks _item_1;
        _item_1 = new Tasks();
        _item_1.uid = _cursor.getInt(_cursorIndexOfUid);
        _item_1.task = _cursor.getString(_cursorIndexOfTask);
        _item_1.startTime = _cursor.getLong(_cursorIndexOfStartTime);
        _item_1.endTime = _cursor.getString(_cursorIndexOfEndTime);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfStatus);
        _item_1.status = _tmp != 0;
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Tasks>> getByStatus(final boolean status) {
    final String _sql = "SELECT * FROM Tasks WHERE status = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final int _tmp;
    _tmp = status ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Tasks"}, false, new Callable<List<Tasks>>() {
      @Override
      public List<Tasks> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfTask = CursorUtil.getColumnIndexOrThrow(_cursor, "task");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<Tasks> _result = new ArrayList<Tasks>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Tasks _item;
            _item = new Tasks();
            _item.uid = _cursor.getInt(_cursorIndexOfUid);
            _item.task = _cursor.getString(_cursorIndexOfTask);
            _item.startTime = _cursor.getLong(_cursorIndexOfStartTime);
            _item.endTime = _cursor.getString(_cursorIndexOfEndTime);
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfStatus);
            _item.status = _tmp_1 != 0;
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
}
