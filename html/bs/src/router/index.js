import VueRouter from 'vue-router'

const Login = () => import ('@/components/Login');
const ResetPassword = () => import ('@/components/ResetPassword');
const Board = () => import('@/components/board/Board');
const AllMessage = () => import('@/components/board/AllMessage');
const MyMessage = () => import('@/components/board/MyMessage');
const SubmitMessage = () => import('@/components/board/SubmitMessage');
const User = () => import('@/components/user/User');
const UserInfo = () => import('@/components/user/UserInfo');
const ResetInfo = () => import('@/components/user/ResetInfo');

const router = new VueRouter({
    routes: [{
        path: '/login',
        component: Login,
        children: [],
    }, {
        path: '/resetPassword',
        component: ResetPassword,
        children: [],
    }, {
        path: '/board',
        component: Board,
        children: [{
            path: 'allMessage',
            component: AllMessage
        }, {
            path: 'myMessage',
            component: MyMessage
        }, {
            path: 'submitMessage',
            component: SubmitMessage
        }],
    }, {
        path: '/user',
        component: User,
        children: [{
            path: 'userInfo',
            component: UserInfo,
            children: []
        },{
            path: 'resetInfo',
            component: ResetInfo,
            children: []
        }]
    }]
})
router.beforeEach((to, from, next) => {
    next();
})

/* 放在跳转到当前页面相同的路由报错 */
const VueRouterPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
export default router