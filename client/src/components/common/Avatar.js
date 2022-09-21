import styled from 'styled-components';

const ProfileURL = styled.img`
  width: ${(props) => props.width || '100%'};
  height: ${(props) => props.height || '100%'};
  border-radius: ${(props) => props.borderRadius || 0};
  box-shadow: 0px 18px 64px -8px rgba(28, 50, 79, 0.38),
    0px 4px 24px -3px rgba(28, 55, 90, 0.16);
  object-fit: cover;
`;
const Avatar = ({ imageURL, ...props }) => {
  return (
    <ProfileURL
      src={imageURL}
      width={props.width}
      height={props.height}
      borderRadius={props.borderRadius}
    />
  );
};

export default Avatar;
