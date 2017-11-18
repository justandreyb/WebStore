const path = require('path');
const NpmInstallPlugin = require('npm-install-webpack-plugin');

module.exports = {
    entry: [
        'babel-polyfill',
        './src/main/js/index'
    ],
    output: {
        path: path.join(__dirname, './target/web_store/resources'), // Change web_store to your target app output
        filename: 'app.bundle.js'
    },
    module: {
        loaders: [
            { test: /\.js$/, loader: 'babel-loader', exclude: /node_modules/, query: {
                presets: ['es2017', 'react']
            }},
            { test: /\.css$/, loader: 'css-loader', exclude: /node_modules/ },
            { test: /\.(jpg|png|svg)$/, loader: 'url-loader', exclude: /node_modules/ },
        ]
    },
    plugins: [
        new NpmInstallPlugin()
    ],
};