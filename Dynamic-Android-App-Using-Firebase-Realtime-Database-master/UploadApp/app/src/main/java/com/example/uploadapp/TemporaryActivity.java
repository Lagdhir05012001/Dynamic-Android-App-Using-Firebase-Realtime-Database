package com.example.uploadapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class TemporaryActivity<Static> extends AppCompatActivity {
        private static final int CHOOSE_IMAGE = 1;
        private static final String TAG = "Image";

        private Button chooseImage, btnUploadImage;
        private TextView viewGallery;
        private ImageView imgPreview;
        private EditText imgname;
        private ProgressBar uploadProgress;
        private EditText imgPrice, imgdesc;
        private Uri imgUrl;
        ArrayList<Upload_Category> mUploads = new ArrayList<Upload_Category>();

        private StorageReference mStorageRef;
        private DatabaseReference mDatabaseRef;
        RecyclerView mRecyclerview;

        private StorageTask mUploadTask;

        TextView add;




    @Override
        protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadProgress = findViewById(R.id.progress_bar);
        chooseImage = findViewById(R.id.button_choose_image);
        btnUploadImage = findViewById(R.id.button_upload);
        viewGallery = findViewById(R.id.text_view_show_uploads);

        imgPrice = findViewById(R.id.edit_text_price);
        imgname = findViewById(R.id.edit_text_file_name);
        imgPreview = findViewById(R.id.image_view);
        imgdesc = findViewById(R.id.description);
        add = findViewById(R.id.add_category);
        mRecyclerview = findViewById(R.id.category_recyclerview);
        //final Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TemporaryActivity.this, New_Category.class));
            }
        });

        final String Category = getIntent().getStringExtra("category");
         ArrayList<String> myList = new ArrayList<>();
         myList = getIntent().getStringArrayListExtra("myArrayList");
         System.out.println("myArrayList"+myList);
        mStorageRef = FirebaseStorage.getInstance().getReference().child(Category);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child(Category);
        System.out.println(mDatabaseRef);



            viewGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TemporaryActivity.this, ImageActivity.class);
                    intent.putExtra("category",Category);
                    startActivity(intent);
                }
            });
            btnUploadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mUploadTask != null && mUploadTask.isInProgress()) {
                        Toast.makeText(TemporaryActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                    } else {
                        uploadImage();
                    }
                }
            });


            chooseImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showFileChoose();
                }
            });
        }


    private void showFileChoose () {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, CHOOSE_IMAGE);
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imgUrl = data.getData();

                Picasso.get().load(imgUrl).into(imgPreview);
            }

        }

        private String getFileExtension (Uri uri){
            ContentResolver contentResolver = getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
        }

        private void uploadImage () {

            if (imgUrl != null) {
                final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                        + "." + getFileExtension(imgUrl));

                fileReference.putFile(imgUrl).continueWithTask(
                        new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                return fileReference.getDownloadUrl();
                            }
                        })
                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    assert downloadUri != null;
                                    Upload upload = new Upload(imgname.getText().toString().trim(), downloadUri.toString(), imgPrice.getText().toString().trim(), imgdesc.getText().toString().trim());
                                    mDatabaseRef.push().setValue(upload);
                                    Toast.makeText(TemporaryActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(TemporaryActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TemporaryActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            } else {
                Toast.makeText(this, "No file selected", Toast.LENGTH_LONG).show();
            }
        }
    }

