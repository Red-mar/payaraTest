import React from 'react';

import {SongList, SongForm, SongQueue} from '../component/Stream.js';

class Stream extends React.Component {
    render() {
        return (<div>
                <h2>Stream</h2>
                <SongList />
                <SongForm />
                <SongQueue />
                </div>);
    }
}

export default Stream;
