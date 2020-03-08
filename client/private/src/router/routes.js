
const routes = [
  {
    path: '/private',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: 'login', component: () => import('pages/Index.vue') },
      { path: 'posts', component: () => import('pages/Index.vue') },
      { path: 'postForm', component: () => import('pages/Index.vue') }
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
