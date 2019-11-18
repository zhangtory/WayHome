export function checkMobile(mobile)
{
  let reg = /^1[3456789]\d{9}$/;
  if (!reg.test(mobile)) {
    return false;
  }
  return true;
}

export function checkEmail(email) {
  let reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
  if(!reg.test(email)){
    return false;
  }
  return true;
}
