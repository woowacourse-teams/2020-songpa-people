const path = require("path");

module.exports = {
  outputDir: path.resolve("__dirname", "../../src/main/resources/static"),
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/": {
        target: "http://localhost:9000",
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
