import logo from './logo.svg';
import './App.css';
import UserComponent from './components/UserComponent';
import socialMediaAuth from './services/auth/auth';
import { githubProvider } from './configs/authMethods';
import firebase from "./configs/firebase-configs";

function App() {
  // let state = {isSignedIn: false};
  // uiConfig = {
  //   signInFlow: 'popup',
  //   signInOptions: [
  //     firebase.auth.GithubAuthProvider.PROVIDER_ID
  //   ],
  //   callbacks: {
  //     signInSuccess: false
  //   }
  // }
  const handleOnClick = async (provider) => {
    const res = await socialMediaAuth(provider);
    console.log(res);
  }

  let componentDidMount = () => {
    firebase.auth().onAuthStateChanged(user => {
      this.setState({ isSignedIn: !!user})
      console.log('user', user);
    });
  }
  
  return (
    <div className="App">
        <UserComponent />

    </div>
  );
}

export default App;
