import React from 'react';
import UserService from '../services/UserService';
import firebase from "../configs/firebase-configs";
import StyledFirebaseAuth from "react-firebaseui/StyledFirebaseAuth";
import { Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

class UserComponent extends React.Component {
    state = { isSignedIn: false};
    uiConfig = {
        signInFlow: 'popup',
        signInOptions: [
          firebase.auth.GithubAuthProvider.PROVIDER_ID
        ],
        callbacks: {
          signInSuccess: () => false
        }
      }

    constructor(props) {
        super(props)
        this.state = {
            users:[],
            isSignedIn: false
        }
    }


    componentDidMount() {
        firebase.auth().onAuthStateChanged(user => {
            this.setState({ isSignedIn: !!user})
            console.log('auth user', user);

            firebase.auth().currentUser.getIdToken(true).then((idToken)=> {
                // this token can be sent to backend
                localStorage.setItem('token', JSON.stringify(idToken));
            }).catch((error) => {
                console.log(error);
            });
        });

        UserService.getUsers().then((res) => {
            this.setState({users: res.data})
            console.log('user', res);
        });
        console.log('state: ', this.state);
    }

    render () {
        return (
            <div>
                {this.state.isSignedIn ? (
                    <div className="pt-2">
                        <span>
                            {/* <Button variant="outline-secondary">Secondary</Button> */}
                            <Button  variant="secondary" onClick={() => firebase.auth().signOut()}>Sign out!</Button>
                        </span>
                        <h1>Welcome {firebase.auth().currentUser.displayName}</h1>
                        <div>
                        <h1 className="text-center">Users List</h1>
                        Data: {user | json}
                            <table className="table table-striped">
                                <thead>
                                    <tr>
                                        <td>User Id</td>
                                        <td>User First Name</td>
                                        <td>User Last Name</td>
                                        <td>User Email </td>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        this.state.users.map(
                                            user =>
                                            <tr key={user.id}>
                                                <td>{user.id}</td>
                                                <td>{user.firstName}</td>
                                                <td>{user.lastName}</td>
                                                <td>{user.emailId}</td>
                                            </tr>
                                        )
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>

                ) : (
                    <div>
                        <h2>Welcome</h2>
                        <h3>Please Sign In</h3>
                        <StyledFirebaseAuth 
                            uiConfig={this.uiConfig}
                            firebaseAuth={firebase.auth()}
                        />
                    </div>
                )}

            </div>
        )
    }
}

export default UserComponent