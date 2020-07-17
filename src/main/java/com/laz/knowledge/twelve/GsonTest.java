package com.laz.knowledge.twelve;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;

public class GsonTest {
	public static void main(String[] args) throws Exception {
		String str = "{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 17:40:08\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 17:40:18\\\"}\",\"createdDate\":\"Apr 21, 2020 5:40:37 PM\",\"docType\":\"obj\",\"Historys\":[{\"TxId\":\"1c9617dfcfd205f38e42457b165d749b49b36b245e6840e4052fb3a50b9d85a8\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"comp_desc\\\":\\\"table构件打包\\\",\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 02:32:10\\\",\\\"type\\\":\\\"add\\\",\\\"operation\\\":\\\"add\\\",\\\"update_date\\\":\\\"2020-04-21 02:32:10\\\"}\",\"createdDate\":\"Apr 21, 2020 2:31:06 PM\",\"docType\":\"obj\",\"Historys\":null}},{\"TxId\":\"3bfb5a535f6930507b531e643a682456b968acfaa1e053628944f266230f0ad4\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 02:35:55\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 02:35:55\\\"}\",\"createdDate\":\"Apr 21, 2020 2:34:51 PM\",\"docType\":\"obj\",\"Historys\":null}},{\"TxId\":\"bc7678b8cd0248d3fe9c7fce4e8e6304dc2b5a1fa7e493df1c6c283a157e44a1\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 16:06:10\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 16:06:10\\\"}\",\"createdDate\":\"Apr 21, 2020 4:06:06 PM\",\"docType\":\"obj\",\"Historys\":null}},{\"TxId\":\"d6919d5fd5c6a5674e1af857d42671e1069bdf910deb519f3570dc0be02ab7ff\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 16:06:46\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 16:06:46\\\"}\",\"createdDate\":\"Apr 21, 2020 4:06:41 PM\",\"docType\":\"obj\",\"Historys\":null}},{\"TxId\":\"0b65f92479eae124b8db175f37b2834a47f4099463eccb0e873855540c2ead7c\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 16:08:53\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 16:08:53\\\"}\",\"createdDate\":\"Apr 21, 2020 4:08:48 PM\",\"docType\":\"obj\",\"Historys\":null}},{\"TxId\":\"13fedc4031cff1b3690227f04e9e8d132c425dd2e1f883411d35870857b9561d\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 16:26:08\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 16:26:08\\\"}\",\"createdDate\":\"Apr 21, 2020 4:25:04 PM\",\"docType\":\"obj\",\"Historys\":null}},{\"TxId\":\"e904ad46aaba980d322d5eeb9ae8328a90a35a5a1f92b17c880fde01fedb6582\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 16:30:29\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 16:30:29\\\"}\",\"createdDate\":\"Apr 21, 2020 4:30:24 PM\",\"docType\":\"obj\",\"Historys\":null}},{\"TxId\":\"da7f12b42e8355d30b132ca31a197113bf5ff52ab4d5837940eead87ffeafced\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 17:38:28\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 17:38:28\\\"}\",\"createdDate\":\"Apr 21, 2020 5:37:24 PM\",\"docType\":\"obj\",\"Historys\":null}},{\"TxId\":\"fac5f3a23645fed8f806940ff51d4bb7ea8f2aa65a793ff8dded11418f648e35\",\"BlockDataEntity\":{\"id\":\"table1.0.0\",\"type\":\"\",\"content\":\"{\\\"state_id\\\":\\\"10\\\",\\\"create_date\\\":\\\"2020-04-21 17:40:08\\\",\\\"type\\\":\\\"download\\\",\\\"operation\\\":\\\"download\\\",\\\"update_date\\\":\\\"2020-04-21 17:40:18\\\"}\",\"createdDate\":\"Apr 21, 2020 5:40:37 PM\",\"docType\":\"obj\",\"Historys\":null}}]}";
		Gson gson = new Gson();
		CodesResponse object = gson.fromJson(str, CodesResponse.class);
		System.out.println(object);
//		DateFormat localFormat
//	      = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
//		Date d = localFormat.parse("Apr 21, 2020 4:25:04 PM");
//		System.out.println(d);
//		DateFormat enUsFormat
//	      = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US);
//		Date d = enUsFormat.parse("Apr 21, 2020 4:25:04 PM");
//		System.out.println(d);
//		CodesResponse c = new CodesResponse();
//		Field field = c.getClass().getDeclaredField("id");
//		field.setAccessible(true);
//		field.set(c,"xx");
//		System.out.println(field.get(c));
	}
}
