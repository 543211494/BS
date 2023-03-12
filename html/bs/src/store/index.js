import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);
/* 用于响应组件中的动作 */
const actions = {
};

const state = {
    isCollapse: localStorage.getItem('isCollapse') === 'true',
};

/* 用于操作数据 */
const mutations = {
    collapseMenu (state) {
        state.isCollapse = !state.isCollapse
        localStorage.setItem('isCollapse', state.isCollapse)
    },
    setCollapse (state) {
        state.isCollapse = true
        localStorage.setItem('isCollapse', state.isCollapse)
    },
    logout (state) {
        localStorage.clear()
        state.isCollapse = true
        localStorage.setItem('isCollapse', state.isCollapse)
    },
};
const store = new Vuex.Store({ actions, mutations, state });
export default store;