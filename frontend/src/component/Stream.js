import React from 'react';
import ReactAudioPlayer from 'react-audio-player';

class SongList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
        };
        this.addToQueue = this.addToQueue.bind(this);
    }

    addToQueue(song) {
        console.log(song);
        const json = {
            method: 'POST',
            credentials: 'include',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: song.id,
                name: song.name,
                url: song.url,
                deleted: song.deleted,
            })
        };
        console.log(json);
        fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/stream/queue', json)
            .then(result=> result.json())
            .catch(error => {console.log(error);});
    }

    componentDidMount() {
        const json = {
            method: 'GET',
            credentials: 'include',
        };
        console.log(json);
        fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/stream/song', json)
            .then(result => result.json())
            .then(items => this.setState({items:items}))
            .catch(error => {
                console.log(error);
            });
    }


    render() {
        return (
                <div>
                <ul class="list-group">
                {this.state.items.length ?
                 this.state.items.map(item =>
                                      <li class="list-group-item" key={item.id}>
                                      Song: {item.name} URI: {item.url}
                                      <button onClick={() => this.addToQueue(item)} class="btn btn-primary">Queue</button>
                                             </li>) :
                        <li class="list-group-item">Empty.</li>
                }
            </ul>
            </div>
        );
    }
}

class SongForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            url: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
    }

    handleSubmit(event) {
        event.preventDefault();
        console.log(this.state);
        const json = {
            method: 'POST',
            credentials: 'include',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: this.state.name,
                url: this.state.url,
                isDeleted: false,
            })
        };
        console.log(json);
        const response = fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/stream/song', json
                              ).then(res => res.json())
              .catch(error => console.error('Error:', error));
    }

    handleChange(event) {
        const target = event.target;

        const value = target.value;
        const name = target.name;

        this.setState(
            {[name]: value}
        );
    }

    render() {
        return (
                <form onSubmit={this.handleSubmit}>
                <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" id="nameInput" name="name" placeholder="Enter name" value={this.state.name} onChange={this.handleChange}/>
                </div>
                <div class="form-group">
                <label>URL</label>
                <input type="text" class="form-control" id="urlInput" name="url" placeholder="Enter url" value={this.state.url} onChange={this.handleChange}/>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
                </form>
        );
    }
}

class SongQueue extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
        };
        this.socket = new WebSocket('ws://localhost:8080/payaraArtifactr3-1.0-SNAPSHOT/queueSock');
        this.handleMessage = this.handleMessage.bind(this);
    }

    handleMessage(data) {
        this.setState({items:data});
    }

    componentDidMount() {
        this.socket.onmessage = ({data}) => this.handleMessage(JSON.parse(data));
    }

    startStreaming() {
        const json = {
            method: 'POST',
            credentials: 'include',
        };
        fetch('https://localhost:8181/payaraArtifactr3-1.0-SNAPSHOT/rest/stream/start', json)
            .catch(error => {
                console.log(error);
            });
    }

    render() {
        return (
                <div>
                <ReactAudioPlayer
            src="http://localhost:8000/test"
            autoPlay
            controls />
                <ul key="queeu" class="list-group">
                {this.state.items.length ?
                 this.state.items.map(item =>
                                      <li class="list-group-item">
                                      Song: {item.name} URI: {item.url}
                                             </li>) :
                        <li class="list-group-item">No songs queued.</li>
                }
            </ul>
                <button class="btn btn-primary" onClick={() => this.startStreaming()}>Start Streaming</button>
            </div>
        );
    }
}

export {
    SongList,
    SongForm,
    SongQueue,
};
