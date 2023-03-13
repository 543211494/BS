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
const SearchMajor = () => import('@/components/SearchMajor');
const SignUp = () => import('@/components/signup/SignUp')

const AdminMain = () => import('@/components/admin/AdminMain')
const AdminHome = () => import('@/components/admin/adminHome/AdminHome')
const AdminMajor = () => import('@/components/admin/adminMajor/AdminMajor')
const AdminMajorAdd = () => import('@/components/admin/adminMajor/AdminMajorAdd')
const AdminMajorUpd = () => import('@/components/admin/adminMajor/AdminMajorUpd')
const AdminNotice = () => import('@/components/admin/adminNotice/AdminNotice')
const AdminNoticeAdd = () => import('@/components/admin/adminNotice/AdminNoticeAdd')
const AdminUser = () => import('@/components/admin/adminUser/AdminUser')
const AdminUserAdd = () => import('@/components/admin/adminUser/AdminUserAdd')

const TeacherMain = () => import('@/components/teacher/TeacherMain')
const TeacherPlan = () => import('@/components/teacher/teacherPlan/TeacherPlan')
const TeacherPlanAdd = () => import('@/components/teacher/teacherPlan/TeacherPlanAdd')
const TeacherPlanUpd = () => import('@/components/teacher/teacherPlan/TeacherPlanUpd')
const TeacherSta = () => import('@/components/teacher/teacherSta/TeacherSta')
const TeacherStu = () => import('@/components/teacher/teacherStu/TeacherStu')
const TeacherStuDetail = () => import('@/components/teacher/teacherStu/TeacherStuDetail')
const TeacherStuScore = () => import('@/components/teacher/teacherStu/TeacherStuScore')

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
        }],
    }, {
        path: '/searchMajor',
        component: SearchMajor,
        children: []
    },{
        path: '/signUp',
        component: SignUp,
        children: []
    },
    
    
    /* 以上是刘智宇添加的页面，以下是郝靖东添加的页面 */
    {
        path: '/admin',
        component: AdminMain,
        redirect: '/admin/home',
        children: [{
            path: '/admin/home',
            component: AdminHome
        }, {
            path: '/admin/major',
            component: AdminMajor
        }, {
            path: '/admin/major/add',
            component: AdminMajorAdd
        }, {
            path: '/admin/major/upd', //query 传参
            component: AdminMajorUpd
        }, {
            path: '/admin/notice',
            component: AdminNotice
        }, {
            path: '/admin/notice/add',
            component: AdminNoticeAdd
        }, {
            path: '/admin/user',
            component: AdminUser
        }, {
            path: '/admin/user/add',
            component: AdminUserAdd
        },]
    }, {
        path: '/teacher',
        component: TeacherMain,
        redirect: '/teacher/plan',
        children: [{
            path: '/teacher/plan',
            component: TeacherPlan
        }, {
            path: '/teacher/plan/add',
            component: TeacherPlanAdd
        }, {
            path: '/teacher/plan/upd', //query 传参
            component: TeacherPlanUpd
        }, {
            path: '/teacher/sta', 
            component: TeacherSta
        }, {
            path: '/teacher/stu',
            component: TeacherStu
        }, {
            path: '/teacher/stu/detail', //query 传参
            component: TeacherStuDetail
        }, {
            path: '/teacher/stu/score',
            component: TeacherStuScore
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