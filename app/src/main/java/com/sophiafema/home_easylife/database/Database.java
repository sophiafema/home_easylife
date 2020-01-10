package com.sophiafema.home_easylife.database;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sophiafema.home_easylife.models.Event;
import com.sophiafema.home_easylife.models.Light;
import com.sophiafema.home_easylife.models.Music;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.models.Shutter;
import com.sophiafema.home_easylife.models.SimpleRoom;
import com.sophiafema.home_easylife.models.Thermostat;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;

public class Database extends AsyncTask<Object, Void, Object> {

    private FirebaseFirestore db;

    /**
     * Room Adapter: aus collections einen Raum generieren
     */


    public Database() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            switch ((String) objects[0])
            {
                case "room":
                    return getRoom((String) objects[1], (String) objects[2]);
                case "function":
                    return getFunction((String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4]);
                case "event":
                    return getEvent((String) objects[1], (String) objects[2]);
                case "events":
                    return getAllEvents((String) objects[1]);
                case "functions":
                    return getAllFromFunction((String) objects[1], (String) objects[2], (String) objects[3]);
                case "roomDoc":
                    return getAllRoomDocId((String) objects[1]);
                case "functionDoc":
                    return getAllFunctionDocId((String) objects[1], (String) objects[2], (String) objects[3]);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static final String LIGHT = "light";
    public static final String THERMOSTAT = "thermostat";
    public static final String SHUTTER = "shutter";
    public static final String MUSIC = "music";
    public static final String EVENT = "event";
    public static final String ROOM = "room";
    public static final String HOUSE = "house";




    private void saveDoc(DocumentReference docRef, Object o) {
        docRef.set(o)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("SUCCESS", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ERROR", "Error writing document", e);
                    }
                });
    }


    /**
     * Update
     * @param room
     * @param function
     * @param id
     * @param field
     * @param value
     */

    public void updateSpecificFunction(String userId, String room, String function, String id, String field, Object value) {
        DocumentReference docRef = getDocumentReferenceFunction(userId, room, function, id);
        docRef.update(
                field, value
        );
    }

    /**
     * set Objects
     * @param room
     * @param function
     * @param o
     * @param id
     */
    public void setFunction(String userId, String room, String function, Object o, String id) {
        DocumentReference docRef = getDocumentReferenceFunction(userId, room, function, id);
        saveDoc(docRef, o);
    }


    public void setRoom(String userId, SimpleRoom d) {
        DocumentReference docRef = getDocumentReferenceRoom(userId, d.getName());
        saveDoc(docRef, d);
    }

    public void setEvent(String userId, Object o, String id) {
        DocumentReference docRef = getDocumentReferenceEvent(userId, id);
        saveDoc(docRef, o);
    }


    /**
     * get single object
     */
    public Object o;
    public Object getFunction(String userId, String room, String function, String id) {
        DocumentReference docRef = getDocumentReferenceFunction(userId, room, function, id);
        Task<DocumentSnapshot> task = docRef.get();
        try {
            DocumentSnapshot doc = Tasks.await(task);
            o = getObjectFromSnapshot(function, doc);
            } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return o;
    }
    public SimpleRoom getRoom(String userId, String room) throws InterruptedException {
        DocumentReference docRef = getDocumentReferenceRoom(userId, room);

        Task<DocumentSnapshot> task = docRef.get();
        try {
            DocumentSnapshot doc = Tasks.await(task);
            o = doc.toObject(SimpleRoom.class);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*Thread t = new Thread(){

            @Override
            public void run() {
                super.run();

                syso
            }
        }.start();


        t.wait();*/

        return (SimpleRoom) o;
    }

    public Room getSRoom(String userId, String room) throws InterruptedException {
        DocumentReference docRef = getDocumentReferenceRoom(userId, room);
        Task<DocumentSnapshot> task = docRef.get();
        try {
            DocumentSnapshot doc = Tasks.await(task);
            SimpleRoom s = doc.toObject(SimpleRoom.class);
            CollectionReference colRef = getCollectionReferenceFunction(userId, room, LIGHT);
            Room r = new Room();
            r.setId(s.getId());
            r.setName(s.getName());
            final ArrayList<Light> lights = new ArrayList<>();
            colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println(getObjectFromSnapshot(LIGHT, document));
                        lights.add((Light) getObjectFromSnapshot(LIGHT, document));
                    }
                }
            });
            r.setLights(lights);
            colRef = getCollectionReferenceFunction(userId, room, SHUTTER);
            final ArrayList<Shutter> shutter = new ArrayList<>();
            colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        shutter.add((Shutter) getObjectFromSnapshot(SHUTTER, document));
                    }
                }
            });
            r.setShutters(shutter);

            DocumentReference docFunc = getDocumentReferenceFunction(userId, room, THERMOSTAT, THERMOSTAT);
            docFunc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    o = getObjectFromSnapshot(THERMOSTAT, documentSnapshot);
                }
            });
            r.setThermo((Thermostat) o);

            docFunc = getDocumentReferenceFunction(userId, room, MUSIC, MUSIC);
            docFunc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    o = getObjectFromSnapshot(MUSIC, documentSnapshot);
                }
            });
            r.setMusic((Music) o);

            o = r;

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return (Room) o;
    }


    public Event getEvent(String userId, String event) {
        DocumentReference docRef = getDocumentReferenceEvent(userId, event);
        Task<DocumentSnapshot> task = docRef.get();
        try {
            DocumentSnapshot doc = Tasks.await(task);
            o = doc.toObject(Event.class);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Event) o;
    }

    /**
     * get Object list
     */
    private ArrayList<Object> functionList = new ArrayList<>();
    public ArrayList<Object> getAllFromFunction(String userId, String room, String function) {
        CollectionReference colRef = getCollectionReferenceFunction(userId, room, function);
        colRef.get().addOnCompleteListener(new OnSuccessList(function));

        Task<QuerySnapshot> task = colRef.get();
        try {
            QuerySnapshot doc = Tasks.await(task);
            for (QueryDocumentSnapshot document : task.getResult()) {
                functionList.add(getObjectFromSnapshot(function, document));
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return functionList;
    }

    private ArrayList<Event> eventList = new ArrayList<>();
    public ArrayList<Event> getAllEvents(String userId) {
        CollectionReference colRef = getCollectionReferenceEvents(userId);

        Task<QuerySnapshot> task = colRef.get();
        try {
            QuerySnapshot doc = Tasks.await(task);
            for (QueryDocumentSnapshot document : task.getResult()) {
                eventList.add((Event) getObjectFromSnapshot(EVENT, document));
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    private ArrayList<String> id = new ArrayList<String>();
    /**
     * get document ids of objects
     * @param userId
     * @param room
     * @param function
     * @return
     */
    public ArrayList<String> getAllFunctionDocId(String userId, String room, String function) {
        CollectionReference colRef = getCollectionReferenceFunction(userId, room, function);
        Task<QuerySnapshot> task = colRef.get();
        try {
            QuerySnapshot doc = Tasks.await(task);
            for (QueryDocumentSnapshot document : task.getResult()) {
                id.add(document.getId());
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }
    public ArrayList<String> getAllRoomDocId(String userId) {
        CollectionReference colRef = getCollectionReferenceRooms(userId);

        Task<QuerySnapshot> task = colRef.get();
        try {
            QuerySnapshot doc = Tasks.await(task);
            for (QueryDocumentSnapshot document : task.getResult()) {
                id.add(document.getId());
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return id;
    }



    private DocumentReference getDocumentReferenceFunction(String userId, String roomId, String function, String functionId) {
        return db.collection(HOUSE).document(userId)
                .collection(ROOM).document(roomId)
                .collection(function).document(functionId);
    }
    private DocumentReference getDocumentReferenceRoom(String userId, String roomId) {
        return db.collection(HOUSE).document(userId)
                .collection(ROOM).document(roomId);
    }
    private DocumentReference getDocumentReferenceEvent(String userId, String id) {
        return db.collection(HOUSE).document(userId)
                .collection(EVENT).document(id);
    }
    private CollectionReference getCollectionReferenceFunction(String userId, String roomId, String function) {
        return db.collection(HOUSE).document(userId)
                .collection(ROOM).document(roomId)
                .collection(function);
    }
    private CollectionReference getCollectionReferenceRooms(String userId) {
        return db.collection(HOUSE).document(userId)
                .collection(ROOM);
    }
    private CollectionReference getCollectionReferenceEvents(String userId) {
        return db.collection(HOUSE).document(userId)
                .collection(EVENT);
    }

    private Object getObjectFromSnapshot(String function, DocumentSnapshot documentSnapshot) {
        o = null;
        switch(function) {
            case ROOM:
                o = documentSnapshot.toObject(SimpleRoom.class);
                break;
            case LIGHT:
                o = documentSnapshot.toObject(Light.class);
                break;
            case THERMOSTAT:
                o = documentSnapshot.toObject(Thermostat.class);
                break;
            case MUSIC:
                o = documentSnapshot.toObject(Music.class);
                break;
            case SHUTTER:
                o = documentSnapshot.toObject(Shutter.class);
                break;
            case EVENT:
                o = documentSnapshot.toObject(Event.class);
                break;
        }
        return o;
    }


    /**
     * On success helper
     */

    class OnSuccessObject implements OnSuccessListener<DocumentSnapshot> {
        String function;
        public OnSuccessObject(String function) {
            this.function = function;
        }

        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            System.out.println("doc "+documentSnapshot);
             o = getObjectFromSnapshot(function, documentSnapshot);
            //transfer=false;
        }
    }


    class OnSuccessList implements OnCompleteListener<QuerySnapshot> {

        String function;
        public OnSuccessList(String function) {
            this.function = function;
        }

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {


            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    functionList.add(getObjectFromSnapshot(function, document));
                }
            } else {
                Log.d("", "Error getting documents: ", task.getException());
            }
        }
    }

    class OnSuccessDocumentId implements OnCompleteListener<QuerySnapshot> {


        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    id.add(document.getId());
                }
            } else {
                Log.d("", "Error getting documents: ", task.getException());
            }
        }
    }

}
