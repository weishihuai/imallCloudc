/**
 * Created by ygw on 2016/6/7.
 */
var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var config = require('./webpack.config');

new WebpackDevServer(webpack(config), {
    headers: { "Access-Control-Allow-Origin": "*" },
    publicPath: "http://localhost:3000/src/main/webapp/WEB-INF/static/dist/",
    hot: true,
    historyApiFallback: true,
}).listen(3000, 'localhost', function (err, result) {
    if (err) console.log(err);
    console.log('Listening at localhost:3000');
});

