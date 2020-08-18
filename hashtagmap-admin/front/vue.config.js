const path = require("path");

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
    proxy: {
      '/': {
        target: "http://localhost:9000",
        ws: true,
        changeOrigin: true
      },
    }
  },
};
