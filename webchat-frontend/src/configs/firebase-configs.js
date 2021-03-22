import firebase from 'firebase';

const firebaseConfig = {
    apiKey: "AIzaSyD2R-LBvka6UYwPWpNymFpEv-DdUxuuUq0",
    authDomain: "my-app-44c65.firebaseapp.com",
    projectId: "my-app-44c65",
    storageBucket: "my-app-44c65.appspot.com",
    messagingSenderId: "465712699903",
    appId: "1:465712699903:web:7f6f85c9e779093d44afca",
    measurementId: "G-8V8T6VZK9S"
  };
  
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  firebase.analytics();

  export default firebase;