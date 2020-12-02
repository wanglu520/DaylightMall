const app = {
  state: () => ({
    asideBar: {
      open: true
    }
  }),
  mutations: {
    changeCollapse (state) {
      state.open = !state.open
    }
  }
}

export default app
