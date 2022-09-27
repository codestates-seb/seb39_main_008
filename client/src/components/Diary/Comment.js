import { useState } from 'react';
import { useForm } from 'react-hook-form';
import Avatar from '../common/Avatar';
import Date from '../common/Date';
import BorderButton from '../common/BorderButton';
import styled from 'styled-components';
import {
  borderRadius,
  colors,
  fontSize,
  space,
} from '../../assets/styles/theme';

const Container = styled.div`
  & > * {
    font-size: ${fontSize.fontSizeS};
    line-height: ${fontSize.fontSizeM};
  }
  width: 100%;
  height: 100%;
  padding: ${space.spaceS};
  display: flex;
  border-bottom: 1px solid ${colors.grey};

  & > img {
    margin-right: ${space.spaceS};
  }
  .edit {
    overflow-y: visible;
    display: block;
    width: 100%;
    height: 100%;
  }
`;
const Right = styled.div`
  width: 100%;
  height: 100%;

  textarea:focus-visible {
    outline: none;
  }

  textarea {
    border: 0.3px solid ${colors.text5};
    border-radius: ${borderRadius.borderRadiusS};
    resize: none;
    white-space: pre-wrap;
  }
`;

const Top = styled.div`
  margin-bottom: ${space.spaceS};
  display: flex;
  align-items: center;

  & > span {
    margin-right: ${space.spaceS};
    color: ${colors.text3};
  }

  & > span + p {
    display: inline-block;
  }

  & > button:nth-child(2) {
    margin-left: auto;
  }

  & > button:nth-child(3) {
    margin-left: auto;
    margin-right: ${space.spaceS};
  }
`;

const Comment = ({ data }) => {
  const [onEdit, setOnEdit] = useState(false);

  const {
    register,
    handleSubmit,
    reset,
    formState: { isSubmitting },
  } = useForm();

  const onUpdateComment = (data) => {
    console.log('update', data);
    setOnEdit(false);
  };

  const onCreateComment = (data) => {
    console.log('add', data);
    reset();
  };

  return (
    <Container>
      <Avatar
        alt={data.nickname}
        imageURL={data.profile}
        width={'50px'}
        height={'50px'}
        borderRadius={borderRadius.borderRadiusL}
      />
      <Right>
        <Top>
          <span>{data.nickname}</span>
          {data.content ? (
            <Date fontsize={fontSize.fontSizeS} date={data.createdAt} />
          ) : null}
          {/* todo 로그인 유저와 댓글 단 유저 비교해서 수정, 삭제 권한 */}
          {data.content ? (
            <>
              <BorderButton
                text="수정"
                onClick={
                  onEdit
                    ? handleSubmit(onUpdateComment)
                    : () => {
                        setOnEdit(true);
                      }
                }
              />
              <BorderButton text="삭제" />
            </>
          ) : (
            <BorderButton
              text="추가"
              onClick={handleSubmit(onCreateComment)}
              disabled={isSubmitting}
            />
          )}
        </Top>
        {onEdit || data.content === undefined ? (
          <form>
            <textarea
              rows="6"
              className="edit"
              defaultValue={data ? data.content : null}
              {...register('content', {
                required: true,
                validate: (value) => {
                  if (value.trim() === '') return false;
                },
              })}
            ></textarea>
          </form>
        ) : (
          <p>{data.content}</p>
        )}
      </Right>
    </Container>
  );
};
export default Comment;
