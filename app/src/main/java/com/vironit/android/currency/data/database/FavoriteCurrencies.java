package com.vironit.android.currency.data.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favorite_currencies")
public class FavoriteCurrencies {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "crypto_id")
    private Integer cryptoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCryptoId() {
        return cryptoId;
    }

    public void setCryptoId(Integer cryptoId) {
        this.cryptoId = cryptoId;
    }
}
