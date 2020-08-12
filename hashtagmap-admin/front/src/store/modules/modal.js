export default {
  namespaced: true,
  state: {
    active: false,
    objectData: {
      type: Object
    },
    arrayData: {
      type: Array
    }
  },
  getters: {
    isActive: state => {
      return state.active;
    }
  },
  mutations: {
    ACTIVATE_MODAL: state => {
      state.active = true;
    },
    DEACTIVATE_MODAL: state => {
      state.active = false;
    }
  }
};
