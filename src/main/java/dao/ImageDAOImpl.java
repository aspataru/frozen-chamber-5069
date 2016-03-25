package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageDAOImpl implements ImageDAO {

	private final MongoClient mongo;
	private final DB db;
	private GridFS gfsImg;

	@SuppressWarnings("deprecation")
	public ImageDAOImpl(String mongoUri, String dbString, String gridFSNamespace) {
		mongo = new MongoClient(new MongoClientURI(mongoUri));
		db = mongo.getDB(dbString);
		gfsImg = new GridFS(db, gridFSNamespace);
	}

	@Override
	public boolean store(String fileURI, String name) {
		try {
			GridFSInputFile gfsFile = gfsImg.createFile(new File(fileURI));
			gfsFile.setFilename(name);
			gfsFile.save();
			return true;
		} catch (IOException e) {
			log.error("Storing failed for fileURI {} and name {}", fileURI, name, e);
			return false;
		}
	}

	@Override
	public boolean retrieve(String fileName, String localURI) {
		try {
			GridFSDBFile imageForOutput = gfsImg.findOne(fileName);
			imageForOutput.writeTo(localURI);
			return true;
		} catch (IOException e) {
			log.error("Retrieval failed for fileName {} and localURI {}", fileName, localURI, e);
			return false;
		}
	}

	@Override
	public boolean remove(String fileName) {
		try {
			gfsImg.remove(gfsImg.findOne(fileName));
			return true;
		} catch (MongoException e) {
			log.error("Removal failed for fileName {}", fileName, e);
			return false;
		}
	}

	@Override
	public List<String> listContents() {
		List<String> contents = new ArrayList<>();
		try {
			DBCursor cursor = gfsImg.getFileList();
			while (cursor.hasNext()) {
				contents.add(cursor.next().toString());
			}

		} catch (Exception e) {
			log.error("Unable to list contents", e);
		}
		return contents;
	}

}