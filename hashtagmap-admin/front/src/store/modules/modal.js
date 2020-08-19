export default {
  namespaced: true,

  state: {
    status: [
      {
        modalName: "",
        active: false
      }
    ],
    objectData: {
      type: Object
    },
    arrayData: {
      type: Array
    }
  },

  getters: {
    isActive: state => modalName => {
      const status = state.status.find(
        status => status.modalName === modalName
      );
      if (!status) {
        return false;
      }
      return status.active;
    }
  },

  mutations: {
    ACTIVATE_MODAL: (state, modalName) => {
      let status = state.status.find(status => status.modalName === modalName);
      if (!status) {
        status = { modalName: modalName, active: false };
        state.status.push(status);
      }
      status.active = true;
    },
    DEACTIVATE_MODAL: (state, modalName) => {
      const status = state.status.find(
        status => status.modalName === modalName
      );
      if (!status) {
        return;
      }
      status.active = false;
    }
  }
};
