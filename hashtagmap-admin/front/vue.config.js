const path = require("path");
const port = 3000;
const proxyPort = 8080;

module.exports = {
  outputDir: path.resolve("__dirname", "../../src/main/resources/static"),
  transpileDependencies: ["vuetify"],
  configureWebpack: {
    resolve: {
      alias: {
        "@": path.join(__dirname, "src/")
      }
    }
  },
  devServer: {
    port: port,
    proxy: {
      "/": {
        target: `http://localhost:${proxyPort}`,
        ws: true,
        changeOrigin: true
      }
    }
  }
};
