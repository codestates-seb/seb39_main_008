import { ReactComponent as LogoSvg } from '../../assets/logo.svg';
//width||100% , height||100%
const Logo = (props) => {
  return <LogoSvg width={props.width || ''} height={props.height || 'auto'} />;
};

export default Logo;
