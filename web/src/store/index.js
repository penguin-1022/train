import { createStore } from 'vuex'

const MEMBER = "MEMBER";

export default createStore({
  state: {
    // || {}是前端用来避免空指针异常的一种写法
    member: window.SessionStorage.get(MEMBER) || {}
  },
  getters: {
  },
  mutations: {
    setMember (state, _member) {
      state.member = _member;
      window.SessionStorage.set(MEMBER, _member);
    }
  },
  actions: {
  },
  modules: {
  }
})
