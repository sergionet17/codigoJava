/**
 * Copyright (C) 2015 Alfresco Software Limited.
 * <p/>
 * This file is part of the Alfresco SDK Samples project.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.gov.movilidadbogota.cmis.database;


import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.model.Filters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.io.*;
import java.sql.SQLException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;

/**
 * Use MongoDB
 *  db.createUser({user:"alfresco",pwd:"1234",roles:["readWrite"]});
 * @author martin.bergljung@alfresco.com
 * @author Ingenian Software
 */
public class MongoDatabaseAdapter implements DatabaseAdapter{
    private static final Log LOG = LogFactory.getLog("org.alfresco.repo");

    private GridFSBucket gridFSBucket;

    /**
     * Esto crea un adaptador vacio. Hasta que no se pongan las propiedades no va a poder usarse.
     */
    public MongoDatabaseAdapter() {

    }

    /**
     * Cambia las propiedades de la base de datos, e inicializa el adaptador.
     * @param databaseProperties
     */
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        initialize(databaseProperties);
    }

    /**
     * Initialize the DB content store by loading the database driver and
     * connecting to the database.
     *
     */
    private void initialize(DatabaseProperties databaseProperties) {
        //@TODO: Hacer que esto sea una propiedad del bean

        StringBuilder mongoConnectionString = new StringBuilder("mongodb://");
        mongoConnectionString.append(databaseProperties.getUser()).append(":").append(databaseProperties.getPassword());
        mongoConnectionString.append("@").append(databaseProperties.getHostname()).append("/");
        mongoConnectionString.append("?authSource=").append(databaseProperties.getDatabaseName()).append("&ssl=false");

        if (LOG.isInfoEnabled()) {
            LOG.info("Connecting to MongoDB: " + mongoConnectionString.toString() + " ...");
        }
        try {
            MongoClientURI uri = new MongoClientURI(mongoConnectionString.toString());
            MongoClient mongoClient = new MongoClient(uri);
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseProperties.getDatabaseName());
            gridFSBucket = GridFSBuckets.create(mongoDatabase);
            if (LOG.isInfoEnabled()) {
                LOG.info("Initialization Complete");
            }
        } catch (Exception se) {
            if (LOG.isErrorEnabled()) {
                LOG.error("Initialization Error, MongoDB connection failed: " + se.getMessage());
            }
        }
    }

    public InputStream getObject(String url) throws SQLException {
        System.out.println("Getting URL: "+url);
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(url);
        return downloadStream;
    }

    public void storeObject(String url, InputStream is, long size) throws SQLException {

        try {
            GridFSUploadOptions options = new GridFSUploadOptions();
            ObjectId fileId = gridFSBucket.uploadFromStream(url, is, options);
           System.out.println("Uploaded to the database: "+fileId + "URL: "+url);

            if (LOG.isDebugEnabled()) {
                LOG.debug("Stored content in Mongo [url=" + url + "][size=" + size + "]");
            }
        } finally {
        }
    }

    public void deleteObject(String url) throws SQLException {
        ObjectId fileId = null;
        //@TODO: Find fileId from URL
        gridFSBucket.delete(fileId);
    }

    public long getObjectSize(String url) throws SQLException {
        System.out.println("ObjectSize for "+url);
        //@TODO: Find fileId from URL
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(url);
        int fileLength = (int) downloadStream.getGridFSFile().getLength();
        System.out.println("Returning "+fileLength);
        return fileLength;
    }

    public boolean isObjectAvailable(String url) throws SQLException {

        GridFSFindIterable iterable = gridFSBucket.find(Filters.eq("filename", url));
        if(iterable==null||iterable.first()==null) {
            if (LOG.isErrorEnabled()) {
                LOG.error("Could not find "+url+ " in the repo");
            }
            return false;
        } else {
            return true;
        }

    }
    public  static void main(String args[]) {
        String url = "db://869842bd-67c4-42d5-bdce-ffe9d1c4fe8d.bin";
        DatabaseProperties props = new DatabaseProperties();
        props.setDatabaseName("alfresco");
        props.setHostname("localhost");
        props.setUser("prueba");
        props.setPassword("prueba");
        MongoDatabaseAdapter mda = new MongoDatabaseAdapter();
        mda.setDatabaseProperties(props);
        File f = new File("/Users/foxtrot/Desktop/screenshots/Screen Shot 2017-08-04 at 8.51.13 AM.png");
        long size = f.length();

        try {
            FileInputStream fis = new FileInputStream(f);
            mda.storeObject(url, fis, size);
            InputStream stream = mda.getObject(url);
            System.out.println("There are "+stream.available() + " bytes to read");
            File targetFile = new File("/Users/foxtrot/Desktop/test.png");
            OutputStream outStream = new FileOutputStream(targetFile);
            IOUtils.copy(stream, outStream);
            assert mda.isObjectAvailable(url);
        } catch(Exception e) {
            e.printStackTrace();
        }
        DbContentStore dbcs = new DbContentStore();
        dbcs.setDatabaseAdapter(mda);
        assert dbcs.isContentUrlSupported(url);
        assert dbcs.isWriteSupported();
        assert dbcs.exists(url);
    }
}
