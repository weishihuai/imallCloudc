ALTER TABLE t_shp_temperature_moisture_monitor_record DROP COLUMN STORAGE_SPACE_NM;
ALTER TABLE t_shp_temperature_moisture_monitor_record ADD COLUMN STORAGE_SPACE_ID bigint not null comment '货位 ID';