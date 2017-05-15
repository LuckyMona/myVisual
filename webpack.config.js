var webpack = require("webpack");

module.exports = {
    entry: __dirname + "/public/index.js",

    output: {
        path:__dirname + '/build',
        filename: 'bundle.js'
    },
    devtool: 'eval-source-map',//配置生成Source Maps，选择合适的选项
   module: {
        loaders: [
            {
                test:/\.css$/,
                loader:'style-loader!css-loader'
            }
        ],
    },

    plugins:[
        new webpack.ProvidePlugin({
            $:"jquery",
            jQuery:"jquery",
            "window.jQuery":"jquery"
        })
    ],
    devServer: {
        contentBase: "./public",//本地服务器所加载的页面所在的目录
        historyApiFallback: true,//不跳转
        inline: true//实时刷新
    }

};
