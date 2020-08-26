const path = require("path");
const port = 8080;
const proxyPort = 9000;

module.exports = {
  outputDir: path.resolve("__dirname", "../../src/main/resources/static"),
  transpileDependencies: ["vuetify"],
  devServer: {
    port: port,
    proxy: {
      "/": {
        target: `http://localhost:${proxyPort}`,
        ws: true,
        changeOrigin: true,
      },
    },
  },
  configureWebpack: {
    resolve: {
      alias: {
        "@": path.join(__dirname, "src/"),
      },
    },
  },
};
