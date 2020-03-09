
const routes = [
  {
    path: '/private',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: 'login', component: () => import('pages/Index.vue') },
      { path: 'posts', component: () => import('pages/Posts.vue') },
      { path: 'postForm', component: () => import('pages/PostForm.vue') },
      { path: 'calculadora', component: () => import('pages/CalculadoraPage.vue') }
    ]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}

export default routes
