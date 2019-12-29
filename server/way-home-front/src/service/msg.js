export function findResultMsg(msg) {
  switch (msg) {
    case 'failure':
      return '请求失败';
    case 'success':
      return '请求成功';
    case 'system_busy':
      return '系统繁忙，请稍后再试';
    case 'token_expired':
      return '登录已过期，请重新登录';
    case 'token_invalid':
      return '未登录，请先登录';
    case 'username_exist':
      return '用户名已存在';
    case 'user_not_exist':
      return '用户名不存在';
    case 'user_or_password_error':
      return '用户名或密码错误';
    case 'user_not_login':
      return '用户未登录';
    case 'repassword_not_same':
      return '两次密码不一致';
    case 'address_not_exist':
      return '地址不存在';
    case 'address_not_init':
      return '地址未初始化';
    case 'sign_error':
      return '签名错误';
    case 'timestamp_error':
      return '时间戳错误';
    case 'password_error':
      return '密码错误';
    default:
      return msg;
  }
}
