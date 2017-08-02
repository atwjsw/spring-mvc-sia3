package com.habuma.spitter.mvc;

import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by wenda on 8/2/2017.
 */
public class AwsS3Test {

    private void saveImage(String filename, MultipartFile image) {
        String s3AccessKey = "";
        String s3SecretKey = "";
        AWSCredentials awsCredentials = new AWSCredentials(s3AccessKey,s3SecretKey);
        S3Service s3 = new RestS3Service(awsCredentials); //creates an instance of JetS3t’s RestS3Service through which it’ll operate on the S3 file system

        S3Bucket imageBucket = null; //Create S3 bucket and object
        try {
            imageBucket = s3.getBucket("spitterImages");

        S3Object imageObject = new S3Object(filename);

        imageObject.setDataInputStream(new ByteArrayInputStream(image.getBytes())); //fills S3Object with image data.
        imageObject.setContentLength(image.getBytes().length);
        imageObject.setContentType("image/jpeg");

        AccessControlList acl=new AccessControlList(); //Set permissions. without it, the images wouldn’t be visible to our application’s users.
        acl.setOwner(imageBucket.getOwner());
        acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
        imageObject.setAcl(acl);

        s3.putObject(imageBucket,imageObject); //Save image

        } catch (S3ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
