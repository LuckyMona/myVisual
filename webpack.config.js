var webpack = require("webpack");
var CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;
module.exports = {

    entry: {
        index: __dirname + "/public/js/index.js",
        appOverview: __dirname + "/public/js/appOverview.js",
        appOverviewSub: __dirname + "/public/js/appOverviewSub.js",
        'vendor': ['jquery']
    },
    output: {
        path: __dirname + '/build',
        filename: "[name].bundle.js",
        publicPath:"/build"
    },
    module: {
        loaders: [
            {
                test:/\.css$/,
                loader:'style-loader!css-loader'
            }
        ],
    },
    // devtool: 'eval-source-map',//配置生成Source Maps，选择合适的选项
    devtool: process.env.WEBPACK_DEVTOOL || 'source-map',
    plugins:[
        new webpack.ProvidePlugin({
            $:"jquery",
            jQuery:"jquery",
            "window.jQuery":"jquery"
        }),

        new CommonsChunkPlugin({
            name: 'vendor',
            minChunks: Infinity
        })
    ],
    devServer: {
        contentBase: "./public",//本地服务器所加载的页面所在的目录
        historyApiFallback: true,//不跳转
        inline: true,//实时刷新
        port:"8081"
    }

};
